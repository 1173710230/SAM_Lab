<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>宿舍信息管理</title>
<link rel="stylesheet" type="text/css"
	href="jquery-easyui-1.3.3/themes/default/easyui.css">
<link rel="stylesheet" type="text/css"
	href="jquery-easyui-1.3.3/themes/icon.css">
<script type="text/javascript" src="jquery-easyui-1.3.3/jquery.min.js"></script>
<script type="text/javascript"
	src="jquery-easyui-1.3.3/jquery.easyui.min.js"></script>
<script type="text/javascript"
	src="jquery-easyui-1.3.3/locale/easyui-lang-zh_CN.js"></script>
<script type="text/javascript">
	var url;

	function searchDormitory() {
		$('#dg').datagrid('load', {
			dormitoryName : $('#s_dormitoryName').val()
		});
	}

	function deleteDormitory() {
		var selectedRows = $("#dg").datagrid('getSelections');
		if (selectedRows.length == 0) {
			$.messager.alert("系统提示", "请选择要删除的数据！");
			return;
		}
		var strIds = [];
		for (var i = 0; i < selectedRows.length; i++) {
			strIds.push(selectedRows[i].id);
		}
		var ids = strIds.join(",");
		$.messager.confirm("系统提示", "您确认要删掉这<font color=red>"
				+ selectedRows.length + "</font>条数据吗？", function(r) {
			if (r) {
				$.post("dormitoryDelete", {
					delIds : ids
				}, function(result) {
					if (result.success) {
						$.messager.alert("系统提示", "您已成功删除<font color=red>"
								+ result.delNums + "</font>条数据！");
						$("#dg").datagrid("reload");
					} else {
						$.messager.alert('系统提示', '<font color=red>'
								+ selectedRows[result.errorIndex].dormitoryName
								+ '</font>' + result.errorMsg);
					}
				}, "json");
			}
		});
	}

	function openDormitoryAddDialog() {
		$("#dlg").dialog("open").dialog("setTitle", "添加宿舍信息");
		url = "dormitorySave";
	}

	function openDormitoryModifyDialog() {
		var selectedRows = $("#dg").datagrid('getSelections');
		if (selectedRows.length != 1) {
			$.messager.alert("系统提示", "请选择一条要编辑的数据！");
			return;
		}
		var row = selectedRows[0];
		$("#dlg").dialog("open").dialog("setTitle", "编辑宿舍信息");
		$("#fm").form("load", row);
		url = "dormitorySave?id=" + row.id;
	}

	function closeDormitoryDialog() {
		$("#dlg").dialog("close");
		resetValue();
	}

	function resetValue() {
		$("#dormitoryName").val("");
		$("#dormitoryDesc").val("");
	}

	function saveDormitory() {
		$("#fm").form("submit", {
			url : url,
			onSubmit : function() {
				return $(this).form("validate");
			},
			success : function(result) {
				if (result.errorMsg) {
					$.messager.alert("系统提示", result.errorMsg);
					return;
				} else {
					$.messager.alert("系统提示", "保存成功");
					resetValue();
					$("#dlg").dialog("close");
					$("#dg").datagrid("reload");
				}
			}
		});
	}
</script>
</head>
<body style="margin: 5px;">
	<table id="dg" title="宿舍信息" class="easyui-datagrid" fitColumns="true"
		pagination="true" rownumbers="true" url="dormitoryList" fit="true"
		toolbar="#tb">
		<thead>
			<tr>
				<th field="cb" checkbox="true"></th>
				<th field="id" width="50">编号</th>
				<th field="dormitoryName" width="100">宿舍名称</th>
				<th field="dormitoryDesc" width="250">宿舍描述</th>
			</tr>
		</thead>
	</table>

	<div id="tb">
		<div>
			<a href="javascript:openDormitoryAddDialog()"
				class="easyui-linkbutton" iconCls="icon-add" plain="true">添加</a> <a
				href="javascript:openDormitoryModifyDialog()"
				class="easyui-linkbutton" iconCls="icon-edit" plain="true">修改</a> <a
				href="javascript:deleteDormitory()" class="easyui-linkbutton"
				iconCls="icon-remove" plain="true">删除</a>
		</div>
		<div>
			&nbsp;宿舍名称：&nbsp;<input type="text" name="s_dormitoryName"
				id="s_dormitoryName" /><a href="javascript:searchDormitory()"
				class="easyui-linkbutton" iconCls="icon-search" plain="true">搜索</a>
		</div>
	</div>

	<div id="dlg" class="easyui-dialog"
		style="width: 400px; height: 280px; padding: 10px 20px" closed="true"
		buttons="#dlg-buttons">
		<form id="fm" method="post">
			<table>
				<tr>
					<td>宿舍名称：</td>
					<td><input type="text" name="dormitoryName" id="dormitoryName"
						class="easyui-validatebox" required="true" /></td>
				</tr>
				<tr>
					<td valign="top">宿舍描述：</td>
					<td><textarea rows="7" cols="30" name="dormitoryDesc"
							id="dormitoryDesc"></textarea></td>
				</tr>
			</table>
		</form>
	</div>

	<div id="dlg-buttons">
		<a href="javascript:saveDormitory()" class="easyui-linkbutton"
			iconCls="icon-ok">保存</a> <a href="javascript:closeDormitoryDialog()"
			class="easyui-linkbutton" iconCls="icon-cancel">关闭</a>
	</div>
</body>
</html>