(function(window){var svgSprite='<svg><symbol id="icon-kaishi" viewBox="0 0 1024 1024"><path d="M511.1340017942123 67.90033499022223C259.67959072869127 67.90033499022223 55.77635881465682 266.6075776695309 55.77635881465682 511.76381925957526S259.5221373332543 955.6273022343902 510.97654710423706 955.6273022343902c251.454411065521 0 455.20018828958024-198.70724267930865 455.20018828958024-443.86348297481476 0.15745468997530865-245.15624159004446-203.5883238286222-443.8634842693531-455.0427335996049-443.8634842693531z m0 824.4303656985284c-215.23993722880002 0-390.17159033236544-170.68038941961478-390.17159033236544-380.4094280337383s175.08910779354076-380.4094280337383 390.17159033236544-380.4094280337383 390.17159033236544 170.68038941961478 390.17159033236544 380.4094280337383-175.08910779354076 380.4094280337383-390.17159033236544 380.4094280337383z m-83.29328891828148-176.5061961197037c-2.5192672723753082 0-5.038535839288888-0.629817465362963-7.242895026251851-1.7319964115753086-5.510898614676542-2.676721962350617-8.97489143782716-8.18762057702716-8.97489143782716-14.170880672553087V337.6194370168098c0-5.825806700088889 3.30653942771358-11.336705314765432 8.659982057876542-14.01342727711605 5.353443924701234-2.676721962350617 11.809068090153087-2.3618138769382715 16.847603929441973 1.1021802407506174l260.1143931233975 176.3487414297284c4.251263683950617 2.991630047762963 6.927985646301234 7.715257801639506 6.927985646301234 12.753792346390124 0 5.195989234725926-2.3618138769382715 9.919616988602469-6.613077560888888 12.91124703636543L437.602875174558 712.8328745212839c-2.991630047762963 2.046904496987654-6.298169475476542 2.991630047762963-9.762162298627159 2.991630047762963z" fill="#1295DB" ></path></symbol></svg>';var script=function(){var scripts=document.getElementsByTagName("script");return scripts[scripts.length-1]}();var shouldInjectCss=script.getAttribute("data-injectcss");var ready=function(fn){if(document.addEventListener){if(~["complete","loaded","interactive"].indexOf(document.readyState)){setTimeout(fn,0)}else{var loadFn=function(){document.removeEventListener("DOMContentLoaded",loadFn,false);fn()};document.addEventListener("DOMContentLoaded",loadFn,false)}}else if(document.attachEvent){IEContentLoaded(window,fn)}function IEContentLoaded(w,fn){var d=w.document,done=false,init=function(){if(!done){done=true;fn()}};var polling=function(){try{d.documentElement.doScroll("left")}catch(e){setTimeout(polling,50);return}init()};polling();d.onreadystatechange=function(){if(d.readyState=="complete"){d.onreadystatechange=null;init()}}}};var before=function(el,target){target.parentNode.insertBefore(el,target)};var prepend=function(el,target){if(target.firstChild){before(el,target.firstChild)}else{target.appendChild(el)}};function appendSvg(){var div,svg;div=document.createElement("div");div.innerHTML=svgSprite;svgSprite=null;svg=div.getElementsByTagName("svg")[0];if(svg){svg.setAttribute("aria-hidden","true");svg.style.position="absolute";svg.style.width=0;svg.style.height=0;svg.style.overflow="hidden";prepend(svg,document.body)}}if(shouldInjectCss&&!window.__iconfont__svg__cssinject__){window.__iconfont__svg__cssinject__=true;try{document.write("<style>.svgfont {display: inline-block;width: 1em;height: 1em;fill: currentColor;vertical-align: -0.1em;font-size:16px;}</style>")}catch(e){console&&console.log(e)}}ready(appendSvg)})(window)