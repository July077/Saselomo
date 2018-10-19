<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<form class="form-inline" action="javascript:;">
	<fieldset>
		<legend><span>基础信息</span></legend>
		<table class="wholeSituationModal">
			<tr>
				<td class="text-right">
					<span>产品名称:</span>
				</td>
				<td>
					<input type="text" id="confirmProNameId" name="confirmProductName" class="form-control" value="自然之礼春生限量礼盒(水润）" readonly>
				</td>
			</tr>
	
			<tr>
				<td class="text-right">
					<span>收货数量:</span>
				</td>
				<td>
					<input type="text" id="confirmCountId" name="confirmCount" class="form-control" placeholder="数量">
					<span class="hintFont">若为空,系统将此子项进行全部收货</span>
				</td>
			</tr>
	
		</table>
	</fieldset>
		
	<div class="row" style="position: relative;top: 10px;height: 60px;">
		<div class="col-md-4 col-md-offset-8">
			<input type="button" value="取消" class="btn btn-sm btn-default">
			<input type="submit" class="btn btn-sm btn-danger" value="确认">
		</div>
	</div>
</form>