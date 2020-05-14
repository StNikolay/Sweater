<#import "common.ftl" as common>
<@common.page title="Sweater">
    <div>
        <h1>List of users:</h1>
        <#if users?has_content>
            <#list users as user>
                <br>
                <div class="card w-75">
                    <div class="card-header">
                        Username: @${user.username}
                    </div>
                    <div class="card-body">
                        Email: ${user.email}
                    </div>
                    <div class="card-footer text-muted">
                        <form action="/users/${user.getId()}" method="post">
                            <input type="submit" value="delete" class="btn btn-danger">
                            <input type="hidden"
                                   name="${_csrf.parameterName}"
                                   value="${_csrf.token}">
                        </form>
                    </div>
                </div>
            </#list>
        <#else>
            <p>No users yet</p>
        </#if>
    </div>
</@common.page>