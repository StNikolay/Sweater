<#import "common.ftl" as common>
<#include "security.ftl">
<@common.page title="Sweater">
        <h3>There will be start page</h3>
        <#if isAdmin>
                <a href="/users">User list</a>
        </#if>
</@common.page>
