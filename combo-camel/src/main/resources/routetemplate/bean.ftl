<!--
	说明:
	针对bean的路由模板
-->
<routes xmlns="http://camel.apache.org/schema/spring">
	<route id="${routePreffix}${routeId}" errorHandlerRef="errorHandler">
		<from uri="${routeFromUri}" />
		<to uri="before:${routeType}?routeId=${routeId}" />
		<toD uri="${propertyNextUri}"/>
		<to uri="next:${routeType}?routeId=${routeId}" />
		<choice>
			<!-- 串行或分支执行 -->
			<when>
				<simple>${propertyType} == 'serial' or ${propertyType} == 'branch'</simple>
				<toD uri="${propertyNextUri}" />
			</when>
			<when>
				<simple>${propertyType} == 'parallel'</simple>
<#--				<!-- 并行执行 &ndash;&gt;-->
<#--				<recipientList parallelProcessing="true" strategyRef="aggregationStrategy">-->
<#--					<simple>${propertyNextUri}</simple>-->
<#--				</recipientList>-->
				<!-- 聚合执行 -->
				<choice>
					<when>
						<simple>${propertyNextUri} != ''</simple>
						<toD uri="${propertyNextUri}" />
					</when>
				</choice>
			</when>
		</choice>
	</route>
</routes>
