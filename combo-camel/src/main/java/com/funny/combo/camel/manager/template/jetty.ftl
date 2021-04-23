<!--
	说明:
	针对jetty的路由模板
-->
<routes xmlns="http://camel.apache.org/schema/spring">
	<route id="${routePreffix}${routeId}" errorHandlerRef="errorHandler">
		<from uri="jetty:http://{{jetty.url}}/${routeUri}" />
		<to uri="before:${routeType}?routeId=${routeId}" />
		<to uri="next:${routeType}?routeId=${routeId}" />
		<choice>
			<!-- 串行或分支执行 -->
			<when>
				<simple>${propertyType} == 'serial' or ${propertyType} == 'branch'</simple>
				<toD uri="${propertyNextUri}" />
			</when>
			<when>
				<simple>${propertyType} == 'parallel'</simple>
				<!-- 并行执行 -->
				<recipientList parallelProcessing="true" strategyRef="aggregationStrategy">
					<simple>${propertyNextUri}</simple>
				</recipientList>
				<!-- 聚合执行 -->
				<choice>
					<when>
						<simple>${propertyNextUri} != ''</simple>
						<toD uri="${propertyNextUri}" />
					</when>
				</choice>
			</when>
		</choice>
		<!-- 控制是否持久化日志 -->
		<choice>
			<when>
				<simple>${propertyPersistSwitch} == 'true'</simple>
				<to ref="persistStage" />
			</when>
		</choice>
	</route>
</routes>