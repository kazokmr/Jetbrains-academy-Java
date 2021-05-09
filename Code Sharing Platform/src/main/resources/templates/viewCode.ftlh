<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Code</title>
    <link rel="stylesheet" href="/css/styles.css">
    <link rel="stylesheet" href="//cdn.jsdelivr.net/gh/highlightjs/cdn-release@10.2.1/build/styles/default.min.css">
</head>
<body>
<div>
    <span id="load_date" class="snippet">${code.formattedUpdateAt}</span>
    <br>
    <#if (code.getViewsRestriction() > 0)>
        <span id="views_restriction">${code.getAvailableNumOfView()}</span> more views allowed
        <br>
    </#if>
    <#if (code.getAvailableSeconds() > 0)>
        The code will be available for <span id="time_restriction">${code.getAvailableSeconds()}</span> seconds
        <br>
    </#if>
    <pre id="code_snippet"><code>${code.code}</code></pre>
</div>
<script src="j//cdn.jsdelivr.net/gh/highlightjs/cdn-release@10.2.1/build/highlight.min.js"></script>
<script>hljs.initHighlightingOnLoad();</script>
</body>
</html>
