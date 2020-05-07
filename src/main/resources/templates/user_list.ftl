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
                </div>
            </#list>
        <#else>
            <p>No users yet</p>
        </#if>
    </div>
</@common.page>