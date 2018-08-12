WorkFlowForm = {}

// 获取表单HTML代码，1工作流实例开始表单，2工作流实例任务表单
WorkFlowForm.GetFormHtml = function(type,key,name) {
	var html;
	if(type == 1) {
		$.post(CTX_PATH+'/workflow/forms/start/get',
			{
				templateKey : key
			},
			function(result){
				var code = JSON.parse(result).resultCode;
				if(code == "200"){
					var wfForm = JSON.parse(result).data;
					wfForm.templateKey = key;
					var render = template.compile(WorkFlowForm.FormTemplate);
					html = render(wfForm);
					
					layer.open({
						type: 1,
						title: '发起流程——'+name,
						skin: 'layui-layer-rim', 
						area: ['720px','560px'], 
						closeBtn: 1,
						content:html
					});
					
				} else {
					layer.msg('加载表单失败', {icon: 2});
				}
			}
		);
	} else {
		$.post(CTX_PATH+'/workflow/forms/task/get',
			{
				taskId : key
			},
			function(result){
				var code = JSON.parse(result).resultCode;
				if(code == "200"){
					var wfForm = JSON.parse(result).data;
					wfForm.taskId = key;
					var render = template.compile(WorkFlowForm.FormTemplate);
					html = render(wfForm);
						
					layer.open({
						type: 1,
						title: '发起流程——'+name,
						skin: 'layui-layer-rim', 
						area: ['720px','560px'], 
						closeBtn: 1,
						content:html
					});
						
				} else {
					layer.msg('加载表单失败', {icon: 2});
				}
			}
		);
	}
	
}

// 开始工作流实例
WorkFlowForm.StartInstance = function(key) {
	var forms = $('#workflow-form').serializeArray();
	var obj = {};
	$.each(forms,function(k,v){ 
		obj[v.name]=v.value; 
    }); 
	var formStr = JSON.stringify(obj);
	
	$.post(CTX_PATH+'/workflow/instances/start',
		{
			templateKey : key,
			formStr : formStr
		},
		function(result){
			var code = JSON.parse(result).resultCode;
			if(code == "200"){
				layer.close(layer.index);
				layer.msg('发起工作流成功', {icon: 1});
			} else {
				layer.msg('发起工作流失败', {icon: 2});
			}
		}
	);
}

//完成工作流任务
WorkFlowForm.ComplateTask = function(key) {
	var forms = $('#workflow-form').serializeArray();
	var obj = {};
	$.each(forms,function(k,v){ 
		obj[v.name]=v.value; 
    }); 
	var formStr = JSON.stringify(obj);
	
	$.post(CTX_PATH+'/workflow/tasks/complate',
		{
			taskId : key,
			formStr : formStr
		},
		function(result){
			var code = JSON.parse(result).resultCode;
			if(code == "200"){
				layer.close(layer.index);
				layer.msg('处理任务成功', {icon: 1});
				$("#main-table").trigger("reloadGrid");
			} else {
				layer.msg('处理任务失败', {icon: 2});
			}
		}
	);
}

// 工作流表单模板
WorkFlowForm.FormTemplate 
	= '<form action="" id="workflow-form" class="smart-form" novalidate="novalidate" style="overflow-x: hidden;" onsubmit="return false;">'
	+ '  <fieldset style="height:400px">'
	+ '  {{each wfFormFields as value i}}'
	+ '    <div class="row">'
	+ '      <section class="col col-2 ">'
	+ '        <label class="label col col-12" style="float:right">{{value.name}}：</label>'
	+ '      </section>'
	+ '      <section class="col col-10 ">'
	+ '      {{if value.type == "enum"}}'
	+ '      <label class="select">'
	+ '        <select id="{{value.id}}" name="{{value.id}}" {{if !value.writable}}disabled="true"{{/if}}>'
	+ '          {{each value.enumRange as range j}}'
	+ '          <option value="{{range.key}}" {{if value.value == range.key}}selected{{/if}}>{{range.value}}</option>'
	+ '          {{/each}}'
	+ '        </select> <i></i>'
	+ '      </label>'
	+ '      {{else}}'
	+ '        <label class="input">'
	+ '          <input type="text" id="{{value.id}}" name="{{value.id}}" value="{{value.value}}" placeholder="请填写{{value.name}}" {{if !value.writable}}disabled="true"{{/if}}>'
	+ '        </label>'
	+ '      {{/if}}'
	+ '      </section>'
	+ '    </div>'
	+ '  {{/each}}'
	+ '  </fieldset>'
	+ '  <footer>'
	+ '    <button type="button" class="btn"onClick="layer.close(layer.index);">取消</button>'
	+ '    {{if templateKey != null && templateKey != ""}}'
	+ '      <button type="submit" class="btn btn-primary" onClick="WorkFlowForm.StartInstance(\'{{templateKey}}\')">提交</button>'
	+ '    {{else}}'
	+ '      <button type="submit" class="btn btn-primary" onClick="WorkFlowForm.ComplateTask(\'{{taskId}}\')">提交</button>'
	+ '    {{/if}}'
	+ '  </footer>'
	+ '</form>';
