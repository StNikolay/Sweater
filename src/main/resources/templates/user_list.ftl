<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <title>User List</title>
    </head>
    <body>
        <a href="/sign_up">Add user</a>
        <#if users?has_content>
            <#list users as user>
                <div>
                    Username: ${user.username}
                </div>
                <div>
                    Email: ${user.email}
                </div>
                <br>
            </#list>
        <#else>
            <p>No users yet</p>
        </#if>
    </body>
</html>