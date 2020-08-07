<#import "common.ftl" as common>
<#include "security.ftl">
<@common.page title="Sweater">
    <#if user.isChatEnabled()>
    <#else>
        <form action="chat/registration" method="get">
            <input class="nav-link" type="submit" value="Registration in chat"/>
        </form>
    </#if>
</@common.page>