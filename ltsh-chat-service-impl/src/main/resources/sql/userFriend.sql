page
===
select @pageTag(){
#use("cols")#
@}
	from user_friend where #use("condition")#
cols
===
  id,create_by,create_time,update_by,update_time,name,type,status,remarks,friend_user_id,sort
condition
===
	1 = 1 
	@if(!isEmpty(createBy)){
	 and create_by=#createBy#
	@}
  order by sort desc
like
===
	1=1
	@if(!isEmpty(id)){
	 and id like #'%'+id+'%'#
	@}
