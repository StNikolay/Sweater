alter table public.t_user
    add column if not exists chat_enabled boolean;

update public.t_user
    set chat_enabled = false
    where chat_enabled is null;