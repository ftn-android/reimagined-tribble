﻿using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

namespace Ftn.Azure_Andorid.Backend.Helpers
{
    public class LatLng
    {
        public double Latitude { get; set; }
        public double Longitude { get; set; }

        public LatLng()
        {
        }

        public LatLng(double lat, double lng)
        {
            this.Latitude = lat;
            this.Longitude = lng;
        }
    }
}