
$.ajax({

    type:"GET",
    url:"http://127.0.0.1:8080/login",
	beforeSend: function(xhr) {
              xhr.setRequestHeader('x-auth-token',"4d0b5989-c0fe-4494-8a2d-54bf3074904b");
         },
    data:{loginName:"admin"},
    success:function(data,xhr,xhr1){
        console.log(data); console.log(xhr1);console.log(xhr1.getResponseHeader('x-auth-token'));
    },
    error:function(data){
        
    }
})

