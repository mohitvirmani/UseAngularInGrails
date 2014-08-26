package com.UseAngularInGrails

import grails.converters.JSON

class HomeController {

	static allowedMethods = [getAllNews: "GET"]

	//First hit, index page, redirected to index.gsp view
	def index() {
		log.debug "index started"
	}
	
	def list(){
		println "got a hit"
		def res = new HashMap()
		res.message="Deepak"
		def data = [:],data1=[:],data2=[:],data3=[:]
		def dataarray = []
		data.put("name", "arvind")
		data.put("nick","89neuron")
		dataarray.add(data)
		data1.put("name", "kakarot")
		data1.put("nick","kakarot +ve")
		dataarray.add(data1)
		data2.put("name","source it open") 
		data2.put("nick", "SIO")
		dataarray.add(data2)
		render dataarray as JSON
	}
	
	def getAllNews(){
		log.debug "getAllNews started"
		def res = new HashMap()
		def news = News.list();
		log.debug "news " + news
		if(news.size()>0){
			res.news =news
			res.status="success"
			res.message="News list coming"
		}else{
			res.status="fails"
			res.message="No News list are coming"
		}

		respond res,[formats:['json', 'xml']];
		return res
	}

	def saveNews(){
		log.debug "Add paramss "+params
		def res = new HashMap()
		res = saveNewsToDB(params)
		respond res,[formats:['json', 'xml']];
		return res
	}

	HashMap saveNewsToDB(params){
		if(params){
			def res = new HashMap()
			def count = Integer.parseInt(params.count)
			for(int i=0;i<=count;i++){
				println i
				News obj = new News();

				if(request.getParameter("Heading"+i))
					obj.heading=request.getParameter("Heading"+i)
				if(request.getParameter("Discription"+i))
					obj.descripton=request.getParameter("Discription"+i)

				def fll=request.getFile("photo"+i).getOriginalFilename()
				log.debug(fll)
				if(fll){
					obj.pic = fll

					if(obj.save(flush:true)){
						def path = 	grailsApplication.config.newsImageLocation+File.separator+obj.id
						File file1 = new File(path)
						if(file1.exists())
						{
							println "File Exist"+file1.exists()
						}else{
							println "File Created "+file1.mkdirs();
						}
						File f=new File(path+File.separator+fll)
						InputStream is = request.getFile("photo"+i).getInputStream()
						OutputStream os = new FileOutputStream(path+File.separator+fll)   //file path
						byte[] buffer = new byte[request.getFile("photo"+i).getSize()]
						int bytesRead
						while ((bytesRead = is.read(buffer)) != -1) {
							os.write(buffer, 0, bytesRead)
						}
						is.close()
						os.close()

						if(f.exists()) {
							obj.picpath = path+File.separator+fll
							obj.save(flush:true)
							res.status="success"
							res.message="News Successfully Saved"
						}else{
							res.status="success"
							res.message="News Successfully Saved"
						}
					} else{
						obj.errors.each {print it}
						res.status="success"
						res.message="News Successfully Saved"
					}
				}
			}
			return res
		}
	}

	def renderImage(){
		def news=News.findById(Long.parseLong(params.id));
		File imageFile=new File(news.picpath)
		if(imageFile.exists()){
			byte[] buffer=new FileInputStream(imageFile).getBytes()
			response.setContentLength(buffer.length)
			response.outputStream.write(buffer)
		}
		else{
			imageFile=new File("/home/mohit/Desktop/koshishkkdk.jpg")
			byte[] buffer=new FileInputStream(imageFile).getBytes()
			response.setContentLength(buffer.length)
			response.outputStream.write(buffer)
		}
	}
}
