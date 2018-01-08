page
===
select @pageTag(){
#use("cols")#
@}
	from message_info_file where #use("condition")#
cols
===
    
        id,
        
        create_by,
        
        create_time,
        
        update_by,
        
        update_time,
        
        message_id,
        
        file_path,
        
        local_path,
        
        file_type,
        
        original_filename
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
        
        @if(!isEmpty(messageId)){
             and message_id=#messageId#
        @}
        
        @if(!isEmpty(filePath)){
             and file_path=#filePath#
        @}
        
        @if(!isEmpty(localPath)){
             and local_path=#localPath#
        @}
        
        @if(!isEmpty(fileType)){
             and file_type=#fileType#
        @}
        
        @if(!isEmpty(originalFilename)){
             and original_filename=#originalFilename#
        @}
    
