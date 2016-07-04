#Interface Document - Rest flavour

User
**Post User** - Signup, returns User

**GetUsers**(string username, string password) - Login
returns list of users, if empty client handles as login failed...
if username and password is empty, return all users


**AddIncident** or **AddGasStation** => **PostLocation**(Location)

**GetLocations**(string filterByType, boolean sendPicturesBack, double? longitude = null, double? latidude = null, double? radius = null) <br>
filterByType == 'incident' => incident<br>
filterByType == 'all' => both<br>
else => gas station<br>
if all 3 coordinate param is present, it will filter the objects by location<br>

see other CRUD operations of needed.

#Install service

You'll need **Visual Studio 2015** with **Azure Developer kit**.
You don't need to have an Azure account set up, you may start the service locally.

The service has a dummy webpage and swagger web page.<br>
Currently hosted on the following link:<br>
http://ftnazure-andoridbackend20160628103944.azurewebsites.net/

Swagger json can be reached from here: <br>
http://ftnazure-andoridbackend20160628103944.azurewebsites.net/swagger/docs/v1<br>

Swagger 2.0 UI can be reached from here:<br>
http://ftnazure-andoridbackend20160628103944.azurewebsites.net/swagger/ui/index<br>


