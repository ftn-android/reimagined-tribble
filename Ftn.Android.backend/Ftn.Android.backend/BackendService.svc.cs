using System;
using System.Collections.Generic;
using System.Linq;
using System.Runtime.Serialization;
using System.ServiceModel;
using System.ServiceModel.Web;
using System.Text;
using Ftn.Android.backend;

namespace Ftn.Android.backend
{
    public class BackendService : IBackendService
    {
        FTNBackendContainer container = new FTNBackendContainer();

        public bool Login(string username, string password)
        {
            using (var container = new FTNBackendContainer())
            {
                var usertemp = container.UserDtoes.Where(u => u.UserName == username && u.Password == password).FirstOrDefault();
                if (usertemp == null)
                    return false;
            }
            return true;
        }
        
        public bool Register(UserDto user)
        {
            using (var container = new FTNBackendContainer())
            {
                var usertemp = container.UserDtoes.Where(u => u.UserName == user.UserName || u.Email == user.Email).FirstOrDefault();
                if (usertemp != null)
                    return false;
                container.UserDtoes.Add(user);
                container.SaveChanges();
            }
            return true;
        }
    }
}
