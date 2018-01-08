page
===
select @pageTag(){
#use("cols")#
@}
	from user_group where #use("condition")#
cols
===
    
        id,
        
        create_by,
        
        create_time,
        
        update_by,
        
        update_time,
        
        name,
        
        type,
        
        status,
        
        owner,
        
        validity,
        
        level_type
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
        
        @if(!isEmpty(name)){
             and name=#name#
        @}
        
        @if(!isEmpty(type)){
             and type=#type#
        @}
        
        @if(!isEmpty(status)){
             and status=#status#
        @}
        
        @if(!isEmpty(owner)){
             and owner=#owner#
        @}
        
        @if(!isEmpty(validity)){
             and validity=#validity#
        @}
        
        @if(!isEmpty(levelType)){
             and level_type=#levelType#
        @}
    
