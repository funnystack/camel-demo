    /** 
     * 2017/06/01 liang.hao ��дgroovy-hint.js 
     * @param {Object} mod 
     */  
    (function(mod) {  
        if (typeof exports == "object" && typeof module == "object") // CommonJS  
            mod(require("../../lib/codemirror"), require("../../mode/groovy/groovy"));  
        else if (typeof define == "function" && define.amd) // AMD  
            define(["../../lib/codemirror", "../../mode/groovy/groovy"], mod);  
        else // Plain browser env  
            mod(CodeMirror);  
    })(function(CodeMirror) {  
        "use strict";  
        var Pos = CodeMirror.Pos;  
        /** 
         * ��groovy.js���ȡkeyword���� 
         * @param {Object} editor 
         */  
        function getKeywords(editor) {  
            var mode = editor.doc.modeOption;  
            if (mode === "groovy") mode = "text/x-groovy";  
            return CodeMirror.resolveMode(mode).keywords;  
        };  
        /** 
         * �ж�Ԫ��item�Ƿ��������arr��   
         * @param {Object} arr 
         * @param {Object} item 
         */  
        function arrayContains(arr, item) { // �ж�Ԫ��item�Ƿ��������arr��    
            if (!Array.prototype.indexOf) {  
                var i = arr.length;  
                while (i--) {  
                    if (arr[i] === item) {  
                        return true;  
                    }  
                }  
                return false;  
            }  
            return arr.indexOf(item) != -1;  
        };  
          
        function hintSql(editor, keywords, tableKeywords, getToken, options) { // ����hint�ĺ��ĺ������ΪvelocityHint(Ҳ���Բ����޸�)    
            // Find the token at the cursor����ȡ��ǰ���ָ�����ַ�    
            var cur = editor.getCursor(),  
                token = getToken(editor, cur),  
                tprop = token;  
            console.log("cur="+cur);
            return {  
                list: getCompletions(token, keywords, tableKeywords, options),  
                from: Pos(cur.line, fetchStartPoint(token)), // �ַ�ƴ�ӵĳ�ʼλ�ã��������Ҫ    
                to: Pos(cur.line, token.end)  
            };  
        };  
        /** 
         * �ַ�ƴ��λ�� 
         * @param {Object} token 
         */  
        function fetchStartPoint(token) {  
            var index = token.string.lastIndexOf("\.");  
            if (index < 0) {  
                return token.start;  
            } else {  
                return token.start + index + 1;  
            }  
            //      return token.start;  
        };  
      
        function sqlHint(editor, options) {  
            var keywords = wordToString(getKeywords(editor)) + CodeMirror.keywords;  
            return hintSql(editor, keywords, CodeMirror.tableKeywords, function(e, cur) {  
                return e.getTokenAt(cur);  
            }, options);  
        };  
        CodeMirror.registerHelper("hint", "groovy", sqlHint);  
        /** 
         * �õ�ƥ��Ĺؼ������� 
         * @param {Object} token 
         * @param {Object} keywords 
         * @param {Object} tableKeywords 
         * @param {Object} options 
         */  
        function getCompletions(token, keywords, tableKeywords, options) {  
            var found = [],  
                start, pointCount, content = token.string;  
            console.log(token);
            if (content && content.length) {  
                start = token.string.charAt(0); //�ַ�����ĸ  
                content = content.trim().substring(0, content.lenght); //������ĸ��Ľ�ȡ  
                pointCount = (start == '\.') ? true : false; //�ж����һ���ַ��Ƿ���.  
            }  
            console.log("content="+content);
            var result = null;  
            if (start && start.trim() != '') { // ������$��ͷ��������ʱ������${}    
                var regexp = new RegExp("\\b" + content + "\\w+\\.?\\b", "gi");  
                if (pointCount && tableKeywords) {  
                    result = tableKeywords.match(regexp);  
                } else {  
                    result = keywords.match(regexp);  
                }  
            }  
            console.log(result);
            if (result && result.length) {  
                for (var i = 0; i < result.length; i++) {
                	var matchChar = result[i];
                	if (content.length <= matchChar.length && pointCount) {  
                		if(matchChar.indexOf(".")==0 || matchChar.indexOf(" ")==0){
                			matchChar = matchChar.substring(1); 
                		}
                		if (matchChar.charAt(matchChar.length-1) == '.') { //如果最后一位是'.'  
                            var name = matchChar.substring(0, matchChar.length - 1);
                            if(!arrayContains(found, name) ){
                            	found.push(name);  
                            }
                        } else {  
                        	 if(!arrayContains(found, matchChar) ){
                        		  found.push(matchChar);  
                             }
                        }  
                    } else {  
                    	if(!arrayContains(found, matchChar)){
                  		  found.push(matchChar);  
                       }
                    }  
                }  
            }  
            return found;  
        };  
        /** 
         * ��ȡ��ǰ�ַ� 
         * @param {Object} str ��ǰ���ַ� 
         * @param {Object} end ����λ�� 
         */  
        function getWord(str, end) {  
            return str.substring(str.lastIndexOf(' '), end);  
        };  
        /** 
         * ��wordlistƴ���ַ� 
         * @param {Object} wordlist 
         */  
        function wordToString(wordlist) {  
            var str = '';  
            for (var word in wordlist) {  
                str += word + ' ';  
            }  
            return str;  
        };  
    });  