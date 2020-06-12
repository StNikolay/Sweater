<#if SPRING_SECURITY_CONTEXT??>
    <#assign
        user = SPRING_SECURITY_CONTEXT.authentication.principal
        name = user.getUsername()
        user_id = user.getId()
        isAdmin = user.isAdmin()
    >
</#if>