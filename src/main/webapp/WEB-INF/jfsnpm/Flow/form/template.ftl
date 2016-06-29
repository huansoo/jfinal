<#assign thisformid=tool.getuuid()>
<#assign thisformoperationid=tool.getuuid()>
<#assign datagridid=tool.getuuid()>

<div class="bjui-pageContent">
    <div data-layout-h="0">
        <div  style="padding-left:0px;padding-right:0px;">
			<!-- 表单开始 -->
			<div class="bjui-pageHeader" style="font-weight:700;font-size:16px;text-align: center;border-left: 1px solid gray;border-right: 1px solid gray;border-top:1px solid gray;">
				${formstruct[0].formDisplayName}
			</div>
			<#if (formdata)?exists>
			<div style="border-left: 1px solid gray;border-right: 1px solid gray;border-bottom:1px solid gray;">
			    <form id="${thisformid!}" action="${rootpath}/Flow/form_save"  class="pageForm" data-toggle="validate" data-reload="true">
			        <input type="hidden" name="__id" value="${formdata.id!}"><!-- 表单的ID -->
			        <input type="hidden" name="__orderid" value="${orderid!}"><!-- 流程实例ID -->
			        <input type="hidden" name="__taskid" value="${taskid!}"><!-- 任务ID -->
			        <input type="hidden" name="__formpath" value="${formpath!}"><!-- 任务路径 -->
			        <input type="hidden" name="__ordertitlecolname" value="${formstruct[0].formUpdateTitle}"><!-- 用此列名更新实例标题 -->
			        <input type="hidden" name="__argscolname" value="<#list formstruct as x><#if x.argsYN == '是'>${x.formColumn},</#if></#list>"><!-- 写入流程参数MAP的列名，逗号分隔 -->
			        <input type="hidden" name="__flowoperationdesc" value="${formstruct[0].formUpdateDesc}"><!-- 写入处理说明的列 -->
			        <input type="hidden" name="__rejto" value="${formstruct[0].formRejTo}"><!-- 指定退回到task，留空退回上一步 -->
			        <div class="pageFormContent">
			            <table class="table table-condensed table-hover" width="100%">
			                <tbody>
			                	<tr>
			                		<td >
			                            <label class="control-label x70">发起时间：</label>
			                            <input type="text" value="${formdata.pm_create_time!}" readonly size="22">
			                        </td>
			                        <td>
			                            <label  class="control-label x70">发起人：</label>
			                            <input type="text" value="${formdata.userName!}" readonly size="22">
			                        </td>
			                	</tr>
			                	<#assign columnhas = 2 />
			                	<#list formstruct as x>
			                		<#if x.type == 'hidden'>
			                		<#elseif x.rowYn == '是' && columnhas = 2 >
			                	<tr>
									<td colspan="2">
									<#elseif x.rowYn == '是' && columnhas = 1>
								</tr>
								<tr>
									<td colspan="2">
										<#assign columnhas = 2 />
									<#elseif x.rowYn != '是' && columnhas = 2>
								<tr>
									<td>
										<#assign columnhas = 1 />
									<#elseif x.rowYn != '是' && columnhas = 1>
									<td>
										<#assign columnhas = 2 />
									</#if>
									
									<#if x.type != 'hidden'>
										<label class="control-label x70">${x.formLabel}：</label>
									</#if>
									<#if x.type == 'text'>
				        				<input type="text" <#if tool.isEmpty(x.defaultValue)>value="${formdata['${x.formColumn}']}"<#else>value="${tool.getDefault(x.defaultValue,userid)}"</#if> 
					        					<#if x.requiredYN == '是'>data-rule="${x.formLabel}:required;"</#if>
					        					<#if x.editYN != '是'>readonly="readonly"</#if>  
					        					<#if x.updateYN == '是'>name="${x.formColumn}"</#if> 
				        					size="<#if x.rowYn == '是'>60<#else>22</#if>">
				        			<#elseif x.type == 'date'>
				        				<input type="text" <#if tool.isEmpty(x.defaultValue)>value="${formdata['${x.formColumn}']}"<#else>value="${tool.getDefault(x.defaultValue,userid)}"</#if> 
				        					<#if x.editYN == '是'>
					        					data-toggle="datepicker" data-pattern="yyyy-MM-dd HH:mm:ss" 
					        					data-rule="<#if x.requiredYN == '是'>${x.formLabel}:required;</#if>datetime;" 
					        				</#if>
					        					<#if x.editYN != '是'>readonly="readonly"</#if>  
					        					<#if x.updateYN == '是'>name="${x.formColumn}"</#if>
				        					size="<#if x.rowYn == '是'>60<#else>22</#if>">
				        			<#elseif x.type == 'findgrid'>
				        				<input type="text" <#if tool.isEmpty(x.defaultValue)>value="${formdata['${x.formColumn}']}"<#else>value="${tool.getDefault(x.defaultValue,userid)}"</#if> 
					        					<#if x.editYN == '是'>
					        					data-toggle="findgrid" 
					        					data-url="${rootpath}/Lookup/${x.otherArgs}"
					        					data-options="{context:'#${thisformid!}',dialogOptions:{width:600,height:400,title:'查询'},${x.otherArgs}}"
					        					</#if>
					        					<#if x.requiredYN == '是'>data-rule="${x.formLabel}:required;"</#if>
					        					readonly="readonly"
					        					<#if x.updateYN == '是'>name="${x.formColumn}"</#if>
				        					size="<#if x.rowYn == '是'>60<#else>22</#if>">
				        			<#elseif x.type == 'hidden'>
				        				<input type="hidden" <#if tool.isEmpty(x.defaultValue)>value="${formdata['${x.formColumn}']}"<#else>value="${tool.getDefault(x.defaultValue,userid)}"</#if> 
					        					<#if x.requiredYN == '是'>data-rule="${x.formLabel}:required;"</#if>
					        					<#if x.editYN != '是'>readonly="readonly"</#if>  
					        					<#if x.updateYN == '是'>name="${x.formColumn}"</#if> 
				        					size="10">
				        			<#elseif x.type == 'textarea'>
				        				<textarea  data-toggle="autoheight" 
					        					<#if x.requiredYN == '是'>data-rule="${x.formLabel}:required;"</#if>
					        					<#if x.editYN != '是'>readonly="readonly"</#if>  
					        					<#if x.updateYN == '是'>name="${x.formColumn}"</#if> 
				        					rows="1" cols="<#if x.rowYn == '是'>60<#else>22</#if>"><#if tool.isEmpty(x.defaultValue)>${formdata['${x.formColumn}']}<#else>${tool.getDefault(x.defaultValue,userid)}</#if></textarea>
				        			<#elseif x.type == 'kindeditor'>
				        				<textarea  data-toggle="kindeditor" 
				        						data-items="'source', '|', 'undo', 'redo', '|', 'preview', 'print', 'cut', 'copy', 'paste','plainpaste', 'wordpaste', '|', 'justifyleft', 'justifycenter', 'justifyright','justifyfull', 'insertorderedlist', 'insertunorderedlist', 'indent', 'outdent', 'subscript','superscript', 'clearhtml', 'quickformat', 'selectall', '|', 'fullscreen', '/','formatblock', 'fontname', 'fontsize', '|', 'forecolor', 'hilitecolor', 'bold','italic', 'underline', 'strikethrough', 'lineheight', 'removeformat', '|', 'image','table', 'hr', 'emoticons', 'pagebreak','anchor', 'link', 'unlink'" 
					        					data-upload-json="${rootpath}/File/kupload" data-file-manager-json="${rootpath}/File/kmanage" 
												<#if x.requiredYN == '是'>data-rule="${x.formLabel}:required;"</#if>
					        					<#if x.editYN != '是'>data-readonly-mode=true</#if>  
					        					<#if x.updateYN == '是'>name="${x.formColumn}"</#if> 
				        					rows="1" cols="<#if x.rowYn == '是'>60<#else>22</#if>"><#if tool.isEmpty(x.defaultValue)>${formdata['${x.formColumn}']}<#else>${tool.getDefault(x.defaultValue,userid)}</#if></textarea>
				        			</#if>
									<#if x.type == 'hidden'>
									<#else>
									</td>
									</#if>
									<#if x.type == 'hidden'>
									<#else>
										<#if columnhas = 2>
								</tr>
										</#if>
									</#if>
			                	</#list>
								<#if taskid?exists>
			                    <tr>
			                    	<td>
			                    		<ul>
			                    		<input type="hidden" id="${thisformoperationid!}" name="__flowoperation">
			                    		<#if tool.isEmpty(formstruct[0].formOperaType3)>
			                    		<#else>
			                    		<li style="padding-left:4px;padding-right:4px;" class="pull-right"><button type="button" onclick="submitform('${formstruct[0].formOperaType3}')" class="btn-orange" data-icon="share">${formstruct[0].formOperaType3}</button></li>
			                    		</#if>
										<#if tool.isEmpty(formstruct[0].formOperaType2)>
			                    		<#else>
			                    		<li style="padding-left:4px;padding-right:4px;" class="pull-right"><button type="button" onclick="submitform('${formstruct[0].formOperaType2}')" class="btn-red" data-icon="close">${formstruct[0].formOperaType2}</button></li>
			                    		</#if>
										<#if tool.isEmpty(formstruct[0].formOperaType1)>
			                    		<#else>
			                    		<li style="padding-left:4px;padding-right:4px;" class="pull-right"><button type="button" onclick="submitform('${formstruct[0].formOperaType1}')" class="btn-green" data-icon="check">${formstruct[0].formOperaType1}</button></li>
			                    		</#if> 
			                			<li style="padding-left:4px;padding-right:4px;" class="pull-right"><button type="button" onclick="submitform('保存')" class="btn-blue" data-icon="save">保存</button></li>
			            				</ul>
			                    	</td>
			                    	<td>
			                    		<ul>
			                    		<#if tool.isEmpty(formstruct[0].formOperaType3)>
			                    		<#else>
			                    		<label class="control-label x70">转发给：</label>
			                    		<input type="hidden" name="__tousers" size="10">
			                    		<input type="text" size="22" readonly data-toggle="findgrid" name="__tousersname" data-options="{context:'#${thisformid!}',include:'__tousers:id,__tousersname:userName',pk:'id',multiple:true,append:true,dialogOptions:{width:600,height:400,title:'查询用户'}, gridOptions:{dataUrl:'${rootpath}/Lookup/user', columns:[{name:'id', label:'ID'},{name:'userNo', label:'工号'},{name:'userName', label:'姓名'}]}}">
			                    		</#if>
			                			<li class="pull-right"><a href="${rootpath}/Snaker/orderview?orderid=${orderid}" data-toggle="dialog" data-id="snakerorderview" 
                        					data-width="980" data-height="780" data-mask="true" data-type="POST" 
                        					data-title="流程实例流程图" class="btn btn-blue">查看流程图</a></li>
			            				</ul>
			                    	</td>
			                    </tr>
			                    <#else>
			                    <tr>
			                    	<td>
			                    	<ul>
						   				<li class="pull-right">
						   					<a href="${rootpath}/Snaker/orderview?orderid=${orderId}" data-toggle="dialog" data-id="snakerorderview" 
						        					data-width="980" data-height="780" data-mask="true" data-type="POST" 
						        					data-title="流程实例流程图" class="btn btn-blue">查看流程图</a>
						        		</li>
						        		<#if orderState == 1>
						        		<li class="pull-left">
						        			<a href="${rootpath}/Flow/orderTerminate?orderId=${orderId}" class="btn btn-red"
						        				data-toggle="doajax" data-confirm-msg="请确认是否强制终止本流程实例？">强制终止</a>
						        		</li>
						        		</#if>
									</ul>
									</td>
								</tr>
			                    </#if>
			                </tbody>
			            </table>
			        </div>
			        <div >
			            
			        </div>
			    </form>
<script>
function submitform(operation)
{
	$('#${thisformoperationid!}').val(operation);
	$('#${thisformid!}').isValid(function(v){
    	if(v){
    		<#if !tool.isEmpty(formstruct[0].formGridAuth)>
			$('#${datagridid!}').datagrid('saveAll');
			//$('#${datagridid!}').datagrid('refresh');
			</#if>
    		$('#${thisformid!}').bjuiajax('ajaxForm')
    	}
	});
}
</script>				    
			</div>
		</#if>
		<!-- 表单结束 -->
        </div>
        <!-- 表格开始 -->
        <#if (formdatagrid)?exists>
        <#if (formdatagrid?size>0)>
        <div style="padding-left:0px;padding-right:0px;padding-top: 5px;height:300px;">
        	<table id="${datagridid!}" class="table table-bordered"></table>
        </div>
<script type="text/javascript">
$('#${datagridid!}').datagrid({
		gridTitle 		: 	'明细表格' 	,//[可选] 标题。
		columns 		: 	[
		        		  	 		{
		        		  	 			name:'id',
		        		  	 			label:'ID',
		        		  	 			align: 'center',
		        		  	 			width: 20,
		        		  	 			edit:false,
		        		  	 			add:false,
		        		  	 			hide:true
		        		  	 		},
		        		  	 		{
		        		  	 			name:'${formstruct[0].formGridDbKey}',
		        		  	 			label:'关联ID',
		        		  	 			align: 'center',
		        		  	 			width: 20,
		        		  	 			edit:false,
		        		  	 			add:false,
		        		  	 			hide:true
		        		  	 		}
		        		  	 		<#list formdatagrid as gridcolumn>
		        		  	 		,{
		        		  	 			<#if !tool.isEmpty(gridcolumn.formClabel)>
		        		  	 			label:'${gridcolumn.formClabel}',
		        		  	 			</#if>
		        		  	 			<#if !tool.isEmpty(gridcolumn.formCwidth)>
		        		  	 			width:${gridcolumn.formCwidth},
		        		  	 			</#if>
		        		  	 			<#if !tool.isEmpty(gridcolumn.formCalign)>
		        		  	 			align:'${gridcolumn.formCalign}',
		        		  	 			</#if>
		        		  	 			<#if !tool.isEmpty(gridcolumn.formCtype)>
		        		  	 			type:'${gridcolumn.formCtype}',
		        		  	 			</#if>
		        		  	 			<#if !tool.isEmpty(gridcolumn.formCadd)>
		        		  	 			add:${gridcolumn.formCadd},
		        		  	 			</#if>
		        		  	 			<#if !tool.isEmpty(gridcolumn.formCedit)>
		        		  	 			edit:${gridcolumn.formCedit},
		        		  	 			</#if>
		        		  	 			<#if !tool.isEmpty(gridcolumn.formCattrs)>
		        		  	 			attrs:${gridcolumn.formCattrs},
		        		  	 			</#if>
		        		  	 			<#if !tool.isEmpty(gridcolumn.formCrule)>
		        		  	 			rule:'${gridcolumn.formCrule}',
		        		  	 			</#if>
		        		  	 			<#if !tool.isEmpty(gridcolumn.formCitems)>
		        		  	 			items:${gridcolumn.formCitems},
		        		  	 			</#if>
		        		  	 			<#if !tool.isEmpty(gridcolumn.formCrender)>
		        		  	 			render:${gridcolumn.formCrender},
		        		  	 			</#if>
		        		  	 			<#if !tool.isEmpty(gridcolumn.formCpattern)>
		        		  	 			pattern:'${gridcolumn.formCpattern}',
		        		  	 			</#if>
		        		  	 			<#if !tool.isEmpty(gridcolumn.formCcalc)>
		        		  	 			calc:'${gridcolumn.formCcalc}',
		        		  	 			</#if>
		        		  	 			<#if !tool.isEmpty(gridcolumn.formCcalcTit)>
		        		  	 			calcTit:'${gridcolumn.formCcalcTit}',
		        		  	 			</#if>
		        		  	 			<#if !tool.isEmpty(gridcolumn.formCcalcDecimal)>
		        		  	 			calcDecimal:${gridcolumn.formCcalcDecimal},
		        		  	 			</#if>
		        		  	 			<#if !tool.isEmpty(gridcolumn.formChide)>
		        		  	 			hide:${gridcolumn.formChide},
		        		  	 			</#if>
		        		  	 			name:'${gridcolumn.formCname}'
		        		  	 		}
		        		  	 		</#list>
		        		    ],
		dataUrl 		: 	'${rootpath}/Flow/form_grid_get?orderId=${orderid!}&gridDbName=${formstruct[0].formGridDbName}&gridDbKey=${formstruct[0].formGridDbKey}',
		<#if !tool.isEmpty(formstruct[0].formGridAuth)>
		editUrl 		: 	'${rootpath}/Flow/form_grid_edit?orderId=${orderid!}&gridDbName=${formstruct[0].formGridDbName}&gridDbKey=${formstruct[0].formGridDbKey}' ,
		delUrl 			: 	'${rootpath}/Flow/form_grid_delete?orderId=${orderid!}&gridDbName=${formstruct[0].formGridDbName}&gridDbKey=${formstruct[0].formGridDbKey}' ,
		showToolbar 	: 	true 	,
		editMode 		: 	'inline',
		toolbarItem 	: 	'${formstruct[0].formGridAuth},|,save,cancel,|,refresh' 	,
		<#else>
		editUrl 		: 	'' ,
		delUrl 			: 	'' ,
		showToolbar 	: 	false,
		editMode 		: 	false,
		toolbarItem 	: 	'' 	,
		</#if>
		contextMenuB 	: 	false 	,
		loadType 		: 	'POST' 	,
		dataType 		: 	'json' 	,
		local 			: 	'local' ,
		fieldSortable 	: 	true 	,
		filterThead 	: 	false 	,
		sortAll 		: 	true 	,
		filterAll 		: 	true 	,
		filterMult 		: 	true 	,
		linenumberAll 	: 	true 	,
		showLinenumber 	: 	true 	,
		showCheckboxcol : 	false 	,
		showEditbtnscol : 	false 	,
		showTfoot 		: 	true 	,
		columnResize 	: 	true 	,
		columnMenu 		: 	true 	,
		columnShowhide 	: 	true 	,
		columnFilter 	: 	true 	,
		columnLock 		: 	true 	,
		paging 			: 	{pageSize:5, selectPageSize:'5,15,60,100', pageCurrent:1, showPagenum:5, total:0} 	,
		inlineEditMult 	: 	true 	,
		saveAll 		: 	true 	,
		addLocation 	: 	'first'	,
		delType 		: 	'POST' 	,
		delConfirm 		: 	'删除操作无需保存，直接生效，是否继续删除？' 	,
		contextMenuH 	: 	true 	,
		hScrollbar 		: 	false 	,
		fullGrid 		: 	true 	,
		height 			: 	'100%'
	});
//@ sourceURL=dynamicScript.js  
</script>
        </#if>
        </#if>
        <!-- 表格结束 -->
        <div class="col-md-5" style="mini-width: 333px;height:400px;padding-left:0px;padding-right:0px;padding-top: 2px;">
        	<div data-toggle="autoajaxload" data-url="${rootpath}/File/filelist?type=project&rele=${orderid!}&auth=${formstruct[0].formAttAuth}" style="height:400px;"></div>
        </div>
        <div class="col-md-7" style="mini-width: 467px;padding-left:0px;padding-right:0px;padding-top: 2px;">
        	<!-- 历史任务List -->
        	<table class="table table-bordered table-hover">
        		<thead>
        			<tr>
        				<th colspan="5" style="text-align: center;">历史任务</th>
        			</tr>
        			<tr>
        				<th width="140px" align="center">时间</th>
        				<th width="100px" align="center">任务名称</th>
        				<th width="70px" align="center">处理人</th>
        				<th width="70px" align="center">处理结果</th>
        				<th align="center">处理说明</th>
        			</tr>
        		</thead>
        		<tbody>
        			<#list historyTasks as item>
        			<tr>
        				<td align="center">${(item.finish_Time)!}</td>
        				<td align="center">${(item.display_Name)!}</td>
        				<td align="center">${(item.userName)!}</td>
        				<td align="center" class="<#if item.flow_operate='转发'>info<#elseif item.flow_operate='退回' || item.flow_operate='不同意'>danger<#else>success</#if>">${(item.flow_operate)!}</td>
        				<td>${(item.flow_operate_mark)!}</td>
        			</tr>
        			</#list>
        		</tbody>
        	</table>
        </div>
    </div>
</div>