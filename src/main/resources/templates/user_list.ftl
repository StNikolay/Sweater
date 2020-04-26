<#import "common.ftl" as common>
<@common.page title="Sweater">
    <div>
        <h1>List of users:</h1>
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
    </div>
</@common.page>