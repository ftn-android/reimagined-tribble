using System;
using System.Data;
using System.Data.Entity;
using System.Data.Entity.Infrastructure;
using System.Linq;
using System.Net;
using System.Web.Http;
using System.Web.Http.Description;
using Ftn.Azure_Andorid.Backend.Models;

namespace Ftn.Azure_Andorid.Backend.Controllers
{
    public class LocationsController : ApiController
    {
        private FtnAzure_AndoridBackendContext db = new FtnAzure_AndoridBackendContext();

        // GET: api/Locations/{typeFilter}
        public IQueryable<Location> GetLocations(string typeFilter, double? longitude = null, double? latitude = null, double? radius = null)
        {
            long longDateNow = DateTime.UtcNow.ToFileTimeUtc();
            var temp = db.Locations.Where(l => l.EndDate > longDateNow);

            switch (typeFilter.ToLower())
            {
                case "all": break;
                case "incident": temp = temp.Where(l => l.Type == true); break;
                default: temp = temp.Where(l => l.Type == false); break;
            }

            if (longitude.HasValue && latitude.HasValue && radius.HasValue)
            {
                //temp = temp.Where()
            }

            return temp;
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
        public IHttpActionResult PutLocation(int id, Location location)
        {
            if (!ModelState.IsValid)
            {
                return BadRequest(ModelState);
            }

            if (id != location.Id)
            {
                return BadRequest();
            }

            db.Entry(location).State = EntityState.Modified;

            try
            {
                db.SaveChanges();
            }
            catch (DbUpdateConcurrencyException)
            {
                if (!LocationExists(id))
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

        private bool LocationExists(int id)
        {
            return db.Locations.Count(e => e.Id == id) > 0;
        }
    }
}