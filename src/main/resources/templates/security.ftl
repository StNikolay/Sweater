<#if SPRING_SECURITY_CONTEXT??>
    <#assign
        localuser = SPRING_SECURITY_CONTEXT.authentication.principal
        name = localuser.getUsername()
        user_id = localuser.getId()
        isAdmin = localuser.isAdmin()
    >
</#if>