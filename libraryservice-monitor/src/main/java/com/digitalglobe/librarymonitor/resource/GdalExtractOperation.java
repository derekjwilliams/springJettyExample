package com.digitalglobe.librarymonitor.resource;



public class GdalExtractOperation    {
    private final static String GDAL_INFO_PIXELSIZE_START = "Pzzzixel Size = (";
    private final static String GDAL_INFO_GEOTRANSFORM_START = "GeoTransform =";
    private final static String GDAL_INFO_GEOTRANSFORM_END = "Metadata";
    public static double[] extractPixelSize(String gdalInfoOutputLine)

    {

        double[] result = new double[2];
        //System.out.println(gdalInfoOutputLine);
        int pixelStart = gdalInfoOutputLine.indexOf(GDAL_INFO_PIXELSIZE_START);
        if (pixelStart != -1)
        {
            int begin = gdalInfoOutputLine.indexOf(GDAL_INFO_PIXELSIZE_START) + GDAL_INFO_PIXELSIZE_START.length();
            int end = gdalInfoOutputLine.indexOf(')', begin);
            if (end != -1)
            {
                String gdalInfoOutDecimated = gdalInfoOutputLine.substring(begin, end);
                int commaIndex = gdalInfoOutDecimated.indexOf(',');
 
                if (commaIndex != -1)
                {
                    try
                    {
                        result[0] = Double.parseDouble(gdalInfoOutDecimated.substring(0, commaIndex));
                        result[1] = Double.parseDouble(gdalInfoOutDecimated.substring(commaIndex + 1));
                    }
                    catch (NumberFormatException e)
                    {
                        System.out.println(e);
                    }
                }
            }
        }   
        else 
        {
            int transformStart = gdalInfoOutputLine.indexOf(GDAL_INFO_GEOTRANSFORM_START);
            int transformEnd = gdalInfoOutputLine.indexOf(GDAL_INFO_GEOTRANSFORM_END);
            if (transformStart != -1 && transformEnd != -1)
            {
                int begin = gdalInfoOutputLine.indexOf(GDAL_INFO_GEOTRANSFORM_START) + GDAL_INFO_GEOTRANSFORM_START.length();
                final String gdalInfoOutDecimated = gdalInfoOutputLine.substring(begin, transformEnd).trim();
//                final String cleanValues = gdalInfoOutDecimated.replaceAll(" ","").replaceAll("\\n",",");
                final String cleanValues = gdalInfoOutDecimated.replaceAll(" +","").replaceAll("\\r?\\n",",");
                final String[] values = cleanValues.split(",");
for (int i = 0; i < values.length; i++)
                    {
                        System.out.println("2--------------" + values[i]);
                    }
                if (values.length == 6)
                {
/*                    for (int i = 0; i < values.length; i++)
                    {
                        System.out.println("" + values[i]);
                    }
*/                    try {
                        result[0] = Double.parseDouble(values[4]);
                        result[1] = Double.parseDouble(values[2]);
                    }
                    catch (NumberFormatException e)
                    {
                        System.out.println(e);
                    }
                }
            }
        }
        return result;
    }
}