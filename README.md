# ContentExtractorApp

# ContentExtraction
This is a project i developed using various technologies such as java, Spring boot, Java Message Service(Apache ActiveMQ), Alfresco(Content Management System),
Optical Character Recognition, PostgreSQL and aApache Solr(NoSQL).

The application allows a user to upload a document, 
the document uploaded is then received by the application then processed by being converted into an image using programming skills,
then the converted image is sent through to message Queue using JMS , 
there is an active MQ standalone application/service connected to this main app which is used to tract all the images sent to the queue.
All the images in the Queue are then sent to a second application, ACTIVE-MQ-RECEIVER, 
which is a standalone application that i have developed that receives images then after receiving images, 
the application will extract content from the images page by page,
using Optical Character Recognition then the extracted text is sent to the NoSQL database, 
Apache Solr for storage, which will be used to search for a document by using text that is inside the document rather than the actual document name.


