using System;
using System.Data;
using System.Data.Entity;
using System.Data.Entity.Infrastructure;
using System.Linq;
using System.Net;
using System.Web.Http;
using System.Web.Http.Description;
using Ftn.Azure_Andorid.Backend.Models;
using Ftn.Azure_Andorid.Backend.Helpers;
using System.Collections.Generic;

namespace Ftn.Azure_Andorid.Backend.Controllers
{
    public class LocationsController : ApiController
    {
        private FtnAzure_AndoridBackendContext db = new FtnAzure_AndoridBackendContext();

        // GET: api/Locations/{typeFilter}
        public List<Location> GetLocations(string typeFilter, bool returnPictureData, double? longitude = null, double? latitude = null, double? radius = null)
        {
            var longDateNow = DateTimeHelper.ToUnixTime(DateTime.Now);
            var temp = db.Locations.Where(l => l.EndDate > longDateNow);

            switch (typeFilter.ToLower())
            {
                case "all": break;
                case "incident": temp = temp.Where(l => l.Type == true); break;
                default: temp = temp.Where(l => l.Type == false); break;
            }
            var listLocation = temp.ToList();

            if (!returnPictureData)
            {
                listLocation.ForEach(l => l.ImageData = new byte[] { 0 });
            }

            if (longitude.HasValue && latitude.HasValue && radius.HasValue)
            {
                var center = new LatLng(latitude.Value, longitude.Value);

                return listLocation.Where(l => DistanceHelper.HaversineDistance(center, new LatLng(l.Latitude, l.Longitude), DistanceHelper.DistanceUnit.Kilometers) < radius.Value).ToList();
            }

            return listLocation;
        }


        // GET: api/Locations/5
        [ResponseType(typeof(Location))]
        public IHttpActionResult GetLocation(int id)
        {
            Location location = db.Locations.Find(id);
            if (location == null)
            {
                return NotFound();
            }

            return Ok(location);
        }

        // PUT: api/Locations/5
        [ResponseType(typeof(void))]
        public IHttpActionResult PutLocation(string uid, Location location)
        {
            if (uid != location.UID)
            {
                return BadRequest();
            }

            try
            {
                var locationDB = db.Locations.First(e => e.UID == uid);
                db.Entry(locationDB).State = EntityState.Modified;
                locationDB.EndDate = location.EndDate;
                if (string.IsNullOrWhiteSpace(locationDB.ConfirmedFrom))
                { locationDB.ConfirmedFrom = location.ConfirmedFrom; }
                else
                { locationDB.ConfirmedFrom += "," + location.ConfirmedFrom; }

                db.SaveChanges();
            }
            catch (DbUpdateConcurrencyException)
            {
                if (!LocationExists(uid))
                {
                    return NotFound();
                }
                else
                {
                    throw;
                }
            }

            return StatusCode(HttpStatusCode.NoContent);
        }

        // POST: api/Locations
        [ResponseType(typeof(Location))]
        public IHttpActionResult PostLocation(Location location)
        {
            /* if (!ModelState.IsValid)
             {
                 return BadRequest(ModelState);
             }*/

            db.Locations.Add(location);

            db.SaveChanges();

            return CreatedAtRoute("DefaultApi", new { id = location.Id }, location);
        }

        // DELETE: api/Locations/5
        [ResponseType(typeof(Location))]
        public IHttpActionResult DeleteLocation(int id)
        {
            Location location = db.Locations.Find(id);
            if (location == null)
            {
                return NotFound();
            }

            db.Locations.Remove(location);
            db.SaveChanges();

            return Ok(location);
        }

        protected override void Dispose(bool disposing)
        {
            if (disposing)
            {
                db.Dispose();
            }
            base.Dispose(disposing);
        }

        private bool LocationExists(string uid)
        {
            return db.Locations.Count(e => e.UID == uid) > 0;
        }
    }
}