using System;
using System.Collections.Generic;

namespace Ftn.Azure_Andorid.Backend.Models
{
    public class Location
    {
        public int Id { get; set; }
        public double Latitude { get; set; }
        public double Longitude { get; set; }
        public string Name { get; set; }
        public string Description { get; set; }
        public DateTime StartDate { get; set; }
        public DateTime EndDate { get; set; }
        public bool Type { get; set; }

        public byte[] ImageData { get; set; }
    }
}