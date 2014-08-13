package com.UseAngularInGrails

class RecipiesController {

    static allowedMethods = []

    def recipieslist() {
       def res = new HashMap()
	   res.message = "Success"
	   respond res,[formats:['json', 'xml']];
	   return res;
    }
   
}
