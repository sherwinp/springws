<%@tag description="Simple Wrapper Tag" pageEncoding="UTF-8"%>
<!doctype html>
<html lang="en">
<head>
<meta charset="utf-8" />
<meta http-equiv="cache-control" content="no-cache" />
<meta http-equiv="pragma" content="no-cache" />
<meta name="viewport" content="width=device-width, initial-scale=1" />
<title>demo</title>
<link rel="stylesheet"
	href="resources/jstree/themes/default/style.min.css" />
<link rel="stylesheet" href="resources/waitMe.min.css" />
<link href="resources/layout.css" type="text/css" rel="stylesheet" />
<script src="resources/jquery-3.0.0.min.js" type="text/javascript"></script>
</head>
<body class="waitMe_body">
	<div class="waitMe_container progress" style="background: #fff">
		<div style="background: #000"></div>
	</div>
	<jsp:doBody />
	<script src="resources/jstree/jstree.min.js" type="text/javascript"></script>
</body>
</html>