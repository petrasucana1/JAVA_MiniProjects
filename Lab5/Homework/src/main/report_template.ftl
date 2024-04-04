<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Repository Report</title>
</head>
<body>
    <h1>Repository Report</h1>
    <p>Repository Path: ${repository}</p>
    <p>Generated at: ${generatedAt}</p>

    <h2>Folders</h2>
    <ul>
        <#list folders as folder>
            <li>${folder}</li>
        </#list>
    </ul>

    <h2>Files</h2>
    <ul>
        <#list files as file>
            <li>${file}</li>
        </#list>
    </ul>
</body>
</html>
