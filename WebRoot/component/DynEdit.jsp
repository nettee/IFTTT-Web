<%@page import="model.task.*" %>
<%@page import="model.data.*" %>
<%Task edit_task=UserTask.getUserTask((Integer)request.getSession().getAttribute("Edit")).getTask(); %>
<div class="collapsible-header active" style="color:#FFC107">Edit
	Task:<%=edit_task.getName() %></div>
<div class="collapsible-body">
	<form action="/Create" method="post" name="edit">
		<input type="hidden" value="edit" name="submit_type">
		<div class="row">
			<div class="col s12">
				<ul class="tabs">
					<li class="tab col s3 disabled"><a href="#test2"
						style="color:black !important;">If</a></li>
					<li class="tab col s3"><a href="#this_s" class="active">This</a>
					</li>
					<li class="tab col s3 disabled"><a href="#test2"
						style="color:black !important;">Then</a></li>
					<li class="tab col s3"><a href="#that_s">That</a></li>
				</ul>	
				<div id="othis_s" class="col s12">
					<div class="col s3" style="padding-top: 20px">
						<div class="center">
							<img alt="if1" src="assets/weibo.png"
								style="width:50px;height: 40px;">
							<p class="center " style="font-weight: bold;font-size:large;">新浪微博</p>
							<p class="center">
								<input name="group1" type="radio" id="oo1" value="o1" /><label
									for="oo1"></label>
							</p>
							<p class="light center">当指定用户发布包含指定内容的微博时</p>
							<a class="btn" target="_parent" href="/weibo">授权</a>
							<%
								if (session.getAttribute("accessToken") != null) {
							%><p class="center">已授权</p>
							<%
								}
							%>
							<div class="center">
								<div class="mdl-textfield mdl-js-textfield">
									<input class="mdl-textfield__input" id="owc"
										name="weibo_content"> <label
										class="mdl-textfield__label">指定内容</label>
								</div>
							</div>
						</div>
					</div>
					<div class="col s3" style="padding-top: 20px">
						<div class="center">
							<img alt="if1" src="assets/weibo.png"
								style="width:50px;height: 40px;">
							<p class="center " style="font-weight: bold;font-size:large;">新浪微博</p>
							<p class="center">
								<input name="group1" type="radio" id="oo2" value="o2" /><label
									for="oo2"></label>
							</p>
							<p class="light center">当用户在指定时间段内未发布微博时</p>
							<a class="btn" target="_parent" href="/weibo">授权</a>
							<%
								if (session.getAttribute("accessToken") != null) {
							%><p class="center">已授权</p>
							<%
								}
							%>
							<div class="center">
								<div class="mdl-textfield mdl-js-textfield">
									<input class="mdl-textfield__input center" id="owt" type="time"
										name="weibo_time">
								</div>
							</div>
						</div>
					</div>
					<div class="col s3" style="padding-top: 20px">
						<div class="center">
							<img alt="if2" src="assets/mail.png"
								style="width:50px;height: 40px;">
							<p class="center " style="font-weight: bold;font-size:large;">邮件</p>
							<p class="center">
								<input name="group1" type="radio" id="oo3" value="o3" /><label
									for="oo3"></label>
							</p>
							<p class="light center">当指定邮箱收到邮件时</p>
						</div>
						<div class="mdl-textfield mdl-js-textfield">
							<input class="mdl-textfield__input" type="text" id="omn"
								name="mail1_name"> <label class="mdl-textfield__label">邮箱</label>
						</div>
						<div class="center">
							<div class="mdl-textfield mdl-js-textfield">
								<input class="mdl-textfield__input" id="omp" type="password"
									name="mail1_password"> <label
									class="mdl-textfield__label">密码</label>
							</div>
						</div>
					</div>
					<div class="col s3" style="padding-top: 20px">
						<div class="center">
							<img alt="if3" src="assets/clock.png"
								style="width:50px;height: 40px;">
							<p class="center " style="font-weight: bold;font-size:large;">时钟</p>
							<p class="center">
								<input name="group1" type="radio" id="oo4" value="o4" /><label
									for="oo4"></label>
							</p>
							<p class="light center">当前时间为指定时间时</p>
						</div>
						<div class="center">
							<div class="mdl-textfield mdl-js-textfield">
								<input class="mdl-textfield__input center" id="ot" type="time"
									name="time"> <label class="mdl-textfield__label"></label>
							</div>
						</div>
					</div>
				</div>
				<div id="othat_s" class="col s12">
					<div class="col s4" style="padding-top: 20px">
						<div class="center">
							<img alt="if1" src="assets/weibo.png"
								style="width:50px;height: 40px;">
							<p class="center " style="font-weight: bold;font-size:large;">新浪微博</p>
							<p class="center">
								<input name="group2" type="radio" value="o5" id="oo5" /><label
									for="oo5"></label>
							</p>
							<p class="light center">发送微博</p>
							<a class="btn" href="/weibo">授权</a>
							<%
								if (session.getAttribute("accessToken") != null) {
							%><p class="center">已授权</p>
							<%
								}
							%>
							<div class="center">
								<div class="mdl-textfield mdl-js-textfield">
									<input class="mdl-textfield__input" id="opwc"
										name="post_sweibo_content"> <label
										class="mdl-textfield__label">指定内容</label>
								</div>
							</div>
						</div>
					</div>
					<div class="col s4" style="padding-top: 20px">
						<div class="center">
							<img alt="if2" src="assets/mail.png"
								style="width:50px;height: 40px;">
							<p class="center " style="font-weight: bold;font-size:large;">邮件</p>
							<p class="center">
								<input name="group2" type="radio" value="o6" id="oo6" /><label
									for="oo6"></label>
							</p>
							<p class="light center">用指定邮箱向另一个邮箱发送邮件</p>
						</div>
						<div class="row center">
							<!-- 
							<div class="mdl-textfield mdl-js-textfield">
								<input class="mdl-textfield__input" id="omn2"	name="mail2_name"> <label class="mdl-textfield__label">发送邮箱</label>
							</div>
							<div class="mdl-textfield mdl-js-textfield">
								<input class="mdl-textfield__input" id="omp2" type="password" name="mail2_password"> <label class="mdl-textfield__label">密码</label>
							</div>
							</div>
							 -->
							<div class="row center">
								<div class="mdl-textfield mdl-js-textfield">
									<input class="mdl-textfield__input" id="omn3"
										name="post_to_address"> <label
										class="mdl-textfield__label">发送至邮箱</label>
								</div>
								<div class="mdl-textfield mdl-js-textfield">
									<input class=" mdl-textfield__input" id="oms"
										name="mail_subject"> <label
										class="mdl-textfield__label">主题</label>
								</div>
								<div class="mdl-textfield mdl-js-textfield">
									<input class=" mdl-textfield__input" id="omc"
										name="mail_content"> <label
										class="mdl-textfield__label">指定内容</label>
								</div>
							</div>
						</div>

					</div>
					<div class="col s4" style="padding-top: 20px">
						<div class="center">
							<img alt="if1" src="assets/user.jpg"
								style="width:50px;height: 40px;">
							<p class="center " style="font-weight: bold;font-size:large;">HelloWorld</p>
							<p class="center">
								<input name="group2" type="radio" value="o7" id="oo7" /><label
									for="oo7"></label>
							</p>
						</div>
					</div>
				</div>

				<div class="center">
					<input class="center btn sub" type="submit" value="Edit">
				</div>

			</div>
		</div>
	</form>
</div>
<script>
//填充表单
	$(document).ready(function(){
	<%if (edit_task.getTrigger().getType()==Trigger.WEIBO_PUSHED) {%>$("[id='oo1']").attr("checked","checked");<%}%>
	<%if (edit_task.getTrigger().getType()==Trigger.WEIBO_SILENT) {%>$("[id='oo2']").attr("checked","checked");<%}%>
	<%if (edit_task.getTrigger().getType()==Trigger.MAIL_RECEIVED) {%>$("[id='oo3']").attr("checked","checked");<%}%>
	<%if (edit_task.getTrigger().getType()==Trigger.TIME) {%>$("[id='oo4']").attr("checked","checked");<%}%>
	<%if (edit_task.getAction().getType()==Action.WEIBO_PUSHING) {%>$("[id='oo5']").attr("checked","checked");<%}%>
	<%if (edit_task.getAction().getType()==Action.MAIL_SENDING) {%>$("[id='oo6']").attr("checked","checked");<%}%>
	<%if (edit_task.getAction().getType()==Action.HELLO) {%>$("[id='oo7']").attr("checked","checked");<%}%>
	});
</script>