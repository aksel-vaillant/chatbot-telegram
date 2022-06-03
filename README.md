# chatbot-telegram
 
Realized by Aksel Vaillant and Clément Le Batard.
CSE students at ENSIM - Le Mans Université [FRANCE]
Under the direction of Mr. Johan Girault

Made with Java and Telegram Api

------------------

### Commands  

    help                            Print this help
    boost                           Send a support message
    joke                            Send a joke
    joke/:id                        Send a joke by id
    joke/:title                     Send a joke by title
    joke "nulle"                    Send a bad joke
    joke "bonne"                    Send a good joke
    weather/:cityName               Send the weater
        - by default, it will send the meteo of Le Mans
   
 Also...
 - Added a secure system with token in the getJoke function   
 - All those functions can send a message with the sendingMessage attribute = true
  
Bonus - you will find with this link the collection of our requests made with PostMan
https://www.postman.com/collections/301652dddcf49f0ba56d
