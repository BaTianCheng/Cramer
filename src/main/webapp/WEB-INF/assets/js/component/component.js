/*************************************************/
/*******************自定义组件******************/
/*************************************************/

(function ($) {
	
	/**部门选择控件**/
    $.fn.departmentZtree = function (options) {
    	$sel = $(this);
    	var defualts = {
    		callback : null
    	}
    	options = $.extend({}, defualts, options);
    	
    	var callback = options.callback;
    	this.init = function(){
    		var zTreeObj;
    		var setting = {};
    		setting.callback = {};
    		setting.callback.onClick = callback;
    		
    		$.post(CTX_PATH+'/auth/departments/tree',{},
    			function(result){
    				var data = JSON.parse(result).data;
    				zTreeObj = $.fn.zTree.init($sel, setting, data);
    				var nodes = zTreeObj.getNodes();
    				if (nodes.length>0) {
    					zTreeObj.selectNode(nodes[0]);
    					zTreeObj.setting.callback.onClick(null, zTreeObj.setting.treeId, nodes[0]);
    				}
    			}
    		);
    		
    	}
    	this.init();
    	return this;
    }
    
    /**人员选择控件，需要配合模版**/
    $.fn.cramerSelUsers = function (options) {
    	$sel = $(this);
    	$sel.on("click", function(){
    		var html = template('component_selUsers_tpl', {});
			layer.open({
				type: 1,
				title: '选择人员',
				skin: 'layui-layer-rim', 
				area: ['520px','600px'], 
				content:html,
				closeBtn:1,
				btn: ['确定', '取消'],
				yes: function(index, layero){
				    alert(1);
				},
				cancel: function(index, layero){
					layer.close(index);
					return false;
				}
			});
    	});
    		
//    		$.post(CTX_PATH+'/auth/departments/tree',{},
//    			function(result){
//    				var data = JSON.parse(result).data;
//    				zTreeObj = $.fn.zTree.init($sel, setting, data);
//    				var nodes = zTreeObj.getNodes();
//    				if (nodes.length>0) {
//    					zTreeObj.selectNode(nodes[0]);
//    					zTreeObj.setting.callback.onClick(null, zTreeObj.setting.treeId, nodes[0]);
//    				}
//    			}
//    		);

    	return this;
    }
    
})(jQuery);