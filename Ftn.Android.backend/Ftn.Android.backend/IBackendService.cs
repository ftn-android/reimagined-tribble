using System;
using System.Collections.Generic;
using System.Linq;
using System.Runtime.Serialization;
using System.ServiceModel;
using System.ServiceModel.Web;
using System.Text;

namespace Ftn.Android.backend
{
    [ServiceContract]
    public interface IBackendService
    {

        [OperationContract]
        bool Login(string username, string password);

        [OperationContract]
        bool Register(UserDto user);
    }
}
