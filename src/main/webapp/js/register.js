jQuery.validator.addMethod("noSpace", function(value, element) { 
    return value == '' || value.trim().length != 0;  
}, "No space please and don't leave it empty");
jQuery.validator.addMethod("customEmail", function(value, element) { 
  return this.optional( element ) || /^([a-zA-Z0-9_\.\-])+\@(([a-zA-Z0-9\-])+\.)+([a-zA-Z0-9]{2,4})+$/.test( value ); 
}, "Please enter valid email address!");
$.validator.addMethod( "alphanumeric", function( value, element ) {
return this.optional( element ) || /^\w+$/i.test( value );
}, "Letters, numbers, and underscores only please" );

//jQuery.validator.setDefaults({
//	debug: true,
//	success: "valid"
//});

var $registrationForm = $('#frmEmp');
if($registrationForm.length){
  $registrationForm.validate({
	  rules:{
          fname: {
              required: true,
              noSpace: true,
              number: false
          },
          lname: {
              required: true,
              noSpace: true
          },
          email: {
              required: true,
              customEmail: true
          },
          number: {
        	  required: true,
        	  number: true,
        	  minlength: 10,
        	  maxlength: 10
        	 
          },
          address: {
              required: true
          },
          gender: {
              required: true
          }
      },
      messages:{
          fname: {
              required: 'Please enter first name!'
          },
          lname: {
              required: 'Please enter last name!'
          },
          email: {
              required: 'Please enter email!',
              //error message for the email field
              email: 'Please enter valid email!'
          },
          number: {
        	  required: 'Please enter contact number!'
          },
          address: {
              required: 'Please enter address!'
          },
          gender: {
        	  required: 'Please select gender!'
          }
      },
      errorPlacement: function(error, element) 
      {
        if (element.is(":radio")) 
        {
            error.appendTo(element.parents('.gender'));
        }
        else if(element.is(":checkbox")){
            error.appendTo(element.parents('.hobbies'));
        }
        else 
        { 
            error.insertAfter( element );
        }
        
       }
  });
}







getall();
var isNew = true;
var studentid = null;




//========================================================================================
$(document).ready(function(){
$("#add").on('click', function(e){
	e.preventDefault();
	
    if($("#frmEmp").valid())
    {    
        var url="";
        var data= "";
        var method;

        if(isNew==true)
        {
            
            url = 'add';
            data = $("#frmEmp").serialize();
            method = 'POST';
            type = 'POST'
            	
        }
        else
        {
        	
        		url = 'update';
                data = $("#frmEmp").serialize() + "&empid=" + empid;
                method = 'POST';
                type = 'POST'
        	
        }
        $.ajax({
            
            type: method,
            url : url,
            dataType: 'JSON',
            data : data,
            
            success:function(data)
            {
                getall();
                
                $('#fname').val("");
    			$('#lname').val("");
    			$('#email').val("");
    			$('#number').val("");
    			$('#address').val("");
    			$('#gender').val("");
                
                if(isNew ==true)
                {
                    alert("Record Added");
                }
                else
                {
                    alert("Record Updated");
                    
                }
            }

        });

    }

});
});

function getall(){
	$('#tbl-student').dataTable().fnDestroy();
	$.ajax({
		url: "showdata",
		type:"GET",
		method: "GET",
		dataType: "JSON",
		
		success: function(data){
			$('#tbl-student').dataTable({
				"aaData": data,
				"scrollX": true,
				"scrollY": true,
				"aoColumns":[
					{"sTitle": "First Name", "mData": "fname"},
					{"sTitle": "Last Name", "mData": "lname"},
					{"sTitle": "Email", "mData": "email"},
					{"sTitle": "Contact", "mData": "number"},
					{"sTitle": "Address", "mData": "address"},
					{"sTitle": "Gender", "mData": "gender"},
					
					{
						"sTitle":
						"Edit",
						"mData": "id",
						"render": function(mData, type, method, row, meta){
							return '<button class= "btn btn-success" style="font-size:14px; box-shadow: none;" onclick="get_details('+ mData+')"><i class="fas fa-pen"></i></button>';
						}
							
					},
					
					{
						"sTitle":
						"Delete",
						"mData": "id",
						"render": function(mData, type, method, row, meta){
							return '<button class= "btn btn-danger" style="font-size:14px;" onclick="get_delete('+ mData+')">Delete</button>';
						}
							
					},
				]
			})
		}
	})
}

function get_details(id){
	$.ajax({
		type: 'POST',
		method: 'POST',
		url:'edit_return',
		data: {"id": id},
		success: function(data){
			isNew=false
			var obj= JSON.parse(data);
			empid=obj[0].id
			$('#fname').val(obj[0].fname);
			$('#lname').val(obj[0].lname);
			$('#email').val(obj[0].email);
			$('#number').val(obj[0].number);
			$('#address').val(obj[0].address);
			$('#gender').val(obj[0].gender);
		}
		
	});
}

function get_delete(id){
	$.ajax({
		url:'delete',
		type: 'POST',
		method: 'POST',
		dataType:'JSON',
		data:{"id": id},
		
		success: function(data){
			alert("Record Deleted");
			getall();
		}
	});
	
}


