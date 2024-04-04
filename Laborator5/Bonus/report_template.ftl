<!DOCTYPE html>
<html>
<head>
    <title>Document Repository Report</title>
    <style>
        body {
            font-family: Arial, sans-serif;
        }
        h1 {
            color: #333;
        }
        ul {
            list-style-type: none;
            padding: 0;
        }
        li {
            margin-bottom: 10px;
        }
        .document {
            border: 1px solid #ccc;
            padding: 10px;
            background-color: #f9f9f9;
        }
    </style>
</head>
<body>
    <h1>Master Repository Report</h1>
    <ul>
    <#list repository as subdirectory>
        <li>
            <h2>${subdirectory.getName()}</h2>
            <ul>
            <#list subdirectory.listFiles() as file>
                <li class="document">
                    <strong>Document Name:</strong> ${file.getName()}<br>
                </li>
            </#list>
            </ul>
        </li>
    </#list>
    </ul>
</body>
</html>
