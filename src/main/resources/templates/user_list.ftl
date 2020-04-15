<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <title>User List</title>
    </head>
    <body>
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
        <form action="/logout" method="post">
            <input type="submit" value="Logout"/>
            <input type="hidden"
                   name="${_csrf.parameterName}"
                   value="${_csrf.token}"/>
        </form>
    </body>
</html>