page
===
select @pageTag(){
#use("cols")#
@}
	from user_group_rel where #use("condition")#
cols
===
    
        id,
        
        create_by,
        
        create_time,
        
        update_by,
        
        update_time,
        
        nick_name,
        
        role,
        
        level,
        
        group_id,
        
        user_id
condition
===
	1 = 1
    
        @if(!isEmpty(id)){
             and id=#id#
        @}
        
        @if(!isEmpty(createBy)){
             and create_by=#createBy#
        @}
        
        @if(!isEmpty(createTime)){
             and create_time=#createTime#
        @}
        
        @if(!isEmpty(updateBy)){
             and update_by=#updateBy#
        @}
        
        @if(!isEmpty(updateTime)){
             and update_time=#updateTime#
        @}
        
        @if(!isEmpty(nickName)){
             and nick_name=#nickName#
        @}
        
        @if(!isEmpty(role)){
             and role=#role#
        @}
        
        @if(!isEmpty(level)){
             and level=#level#
        @}
        
        @if(!isEmpty(groupId)){
             and group_id=#groupId#
        @}
        
        @if(!isEmpty(userId)){
             and user_id=#userId#
        @}
    
