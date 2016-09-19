<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="tmpl" tagdir="/WEB-INF/tags"%>
<tmpl:wrapper>
	<header>
		<ul>
			<li class="icon"><a id="menu-toggle" onclick="javascript:nav_toggle();return false;" href="javascript:void(0);"
				style="color: white; font-size: 2em;">&#9776;</a></li>
			<li></li>
		</ul>
	</header>
	<nav>
		<div class="menu-nav" id="jstree"></div>
	</nav>


	<section>
		<main>
		<div id="main" class="main">
			<form id="form1" method="post" enctype="multipart/form-data">
				<label class="hvert-space">MAIN</label> <label class="hvert-space">MAIN</label>
				<a onclick="javascript:waitonme();" href="<c:url value='/download/internal' />" download="test.txt">Download This File (from project)</a>
				<label class="hvert-space">MAIN</label> <label class="hvert-space">MAIN</label> 
				<input id="files" name="files" type="file" formenctype="multipart/form-data" multiple="multiple"/>
				<input type="submit" onclick="javascript:return onajaxupload();"/>
			</form>
			<form id="form2" action="upload" method="post" enctype="multipart/form-data">
				<label class="hvert-space">MAIN</label> <label class="hvert-space">MAIN</label>
				<input id="files" name="files" type="file" formenctype="multipart/form-data" multiple="multiple"/>
				<input type="submit"/>
			</form>
		</div>
		</main>
		<script type="text/javascript" src="resources/waitMe.min.js"></script>
		<script type="text/javascript" src="resources/waitonme.js"></script>
		<script type="text/javascript" src="resources/feature_drag_drop.js"></script>
	</section>
	<footer> Footer </footer>
	<%@ include file="scriptcontent.jsp"%>
</tmpl:wrapper>