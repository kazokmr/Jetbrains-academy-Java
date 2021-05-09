<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Latest</title>
    <link rel="stylesheet" href="/css/styles.css">
    <link rel="stylesheet" href="//cdn.jsdelivr.net/gh/highlightjs/cdn-release@10.2.1/build/styles/default.min.css">
</head>
<body>
<#list codeList as code>
    <div>
        <span id="load_date" class="snippet">${code.formattedUpdateAt}</span>
        <pre id="code_snippet"><code>${code.code}</code></pre>
    </div>
</#list>
<script src="//cdn.jsdelivr.net/gh/highlightjs/cdn-release@10.2.1/build/highlight.min.js"></script>
<script>hljs.initHighlightingOnLoad();</script>
</body>
</html>
