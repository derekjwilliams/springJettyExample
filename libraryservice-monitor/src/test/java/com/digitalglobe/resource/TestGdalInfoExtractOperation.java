package com.digitalglobe.resource;

import java.io.File;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.Ignore;

import com.digitalglobe.librarymonitor.resource.GdalExtractOperation;

import static org.junit.Assert.assertEquals;

public class TestGdalInfoExtractOperation

{
    @Test
    public void testGetGdalInfoPixelSizeA() throws Exception
    {

        double[] values = GdalExtractOperation.extractPixelSize(getGdalInfo());
        assertEquals(4.500000005E-6, values[0], 0.00000001);
        System.out.println(values[0]);
    }

    @Test
    public void testGetGdalInfoPixelSizeB() throws Exception
    {
        double[] values = GdalExtractOperation.extractPixelSize(getGdalInfoModelTransformationTag());
        assertEquals(0.0001437304454591234, values[0], 0.00000001);
        System.out.println(values[0]);
    }

    private static String getGdalInfo()
    {
        return "Driver: GTiff/GeoTIFF\n" +
            "Files: /content/data/1/country_coverage/052359556-60/10FEB15181012-S3DS_R25C4-052359556060_01_P001.TIF\n" +
            "Size is 8192, 8192\n" +
            "Coordinate System is:\n" +
            "GEOGCS[\"WGS 84\",\n" +
            "    DATUM[\"WGS_1984\",\n" +
            "        SPHEROID[\"WGS 84\",6378137,298.2572235630016,\n" +
            "            AUTHORITY[\"EPSG\",\"7030\"]],\n" +
            "        AUTHORITY[\"EPSG\",\"6326\"]],\n" +
            "    PRIMEM[\"Greenwich\",0],\n" +
            "    UNIT[\"degree\",0.0174532925199433],\n" +
            "    AUTHORITY[\"EPSG\",\"4326\"]]\n" +
            "Origin = (-105.089184000000031,39.371148000000012)\n" +
            "Pixel Size = (0.000004500000005,-0.000004499999999)\n" +
            "Metadata:\n" +
            "  AREA_OR_POINT=Area\n" +
            "  TIFFTAG_IMAGEDESCRIPTION={\n" +
            "  bandList = \n" +
            "  [\n" +
            "    4;\n" +
            "    3;\n" +
            "    2;\n" +
            "  ]\n" +
            "}\n" +
            "  TIFFTAG_DATETIME=2010:05:18 20:16:34\n" +
            "  TIFFTAG_COPYRIGHT=(C) COPYRIGHT 2010 DigitalGlobe, Inc., Longmont CO USA 80503\n" +
            "Image Structure Metadata:\n" +
            "  INTERLEAVE=PIXEL\n" +
            "Corner Coordinates:\n" +
            "Upper Left  (-105.0891840,  39.3711480) (105d 5'21.06\"W, 39d22'16.13\"N)\n" +
            "Lower Left  (-105.0891840,  39.3342840) (105d 5'21.06\"W, 39d20'3.42\"N)\n" +
            "Upper Right (-105.0523200,  39.3711480) (105d 3'8.35\"W, 39d22'16.13\"N)\n" +
            "Lower Right (-105.0523200,  39.3342840) (105d 3'8.35\"W, 39d20'3.42\"N)\n" +
            "Center      (-105.0707520,  39.3527160) (105d 4'14.71\"W, 39d21'9.78\"N)\n" +
            "Band 1 Block=512x512 Type=Byte, ColorInterp=Red\n" +
            "  NoData Value=0\n" +
            "  Overviews: 4096x4096, 2048x2048, 1024x1024, 512x512, 256x256\n" +
            "Band 2 Block=512x512 Type=Byte, ColorInterp=Green\n" +
            "  NoData Value=0\n" +
            "  Overviews: 4096x4096, 2048x2048, 1024x1024, 512x512, 256x256\n" +
            "Band 3 Block=512x512 Type=Byte, ColorInterp=Blue\n" +
            "  NoData Value=0\n" +
            "  Overviews: 4096x4096, 2048x2048, 1024x1024, 512x512, 256x256";
    }

 

    private String getGdalInfoModelTransformationTag()
    {
        return "Driver: GTiff/GeoTIFF\n" +
            "Files: /content/data/001/contentRegistrar/daily_take/20700100FB078800/browse_7A3810BAEAED87653EB422E30E972751.tif\n" +
            "Size is 1062, 7314\n" +
            "Coordinate System is:\n" +
            "GEOGCS[\"WGS 84\",\n" +
            "    DATUM[\"WGS_1984\",\n" +
            "        SPHEROID[\"WGS 84\",6378137,298.257223563,\n" +
            "            AUTHORITY[\"EPSG\",\"7030\"]],\n" +
            "        AUTHORITY[\"EPSG\",\"6326\"]],\n" +
            "    PRIMEM[\"Greenwich\",0],\n" +
            "    UNIT[\"degree\",0.0174532925199433],\n" +
            "    AUTHORITY[\"EPSG\",\"4326\"]]\n" +
            "  GeoTransform =\n" +
            "  13.03316947838998, 0, -0.0001437304454591234\n" +
            "  77.41075049983476, 0.0001437304454591216, 0\n" +
            "Metadata:\n" +
            "  \"NODATA_VALUES=0 0 0\"\n" +
            "  AREA_OR_POINT=Area\n" +
            "  TIFFTAG_RESOLUTIONUNIT=1 (unitless)\n" +
            "  TIFFTAG_XRESOLUTION=1\n" +
            "  TIFFTAG_YRESOLUTION=1\n" +
            "Image Structure Metadata:\n" +
            "  COMPRESSION=YCbCr JPEG\n" +
            "  INTERLEAVE=PIXEL\n" +
            "  SOURCE_COLOR_SPACE=YCbCr\n" +
            "Corner Coordinates:\n" +
            "Upper Left  (  13.0331695,  77.4107505) ( 13d 1'59.41\"E, 77d24'38.70\"N)\n" +
            "Lower Left  (  11.9819250,  77.4107505) ( 11d58'54.93\"E, 77d24'38.70\"N)\n" +
            "Upper Right (  13.0331695,  77.5633922) ( 13d 1'59.41\"E, 77d33'48.21\"N)\n" +
            "Lower Right (  11.9819250,  77.5633922) ( 11d58'54.93\"E, 77d33'48.21\"N)\n" +
            "Center      (  12.5075472,  77.4870714) ( 12d30'27.17\"E, 77d29'13.46\"N)\n" +
            "Band 1 Block=512x512 Type=Byte, ColorInterp=Red\n" +
            "  NoData Value=0\n" +
            "  Overviews: 531x3657, 266x1829, 133x915, 67x458, 34x229\n" +
            "Band 2 Block=512x512 Type=Byte, ColorInterp=Green\n" +
            "  NoData Value=0\n" +
            "  Overviews: 531x3657, 266x1829, 133x915, 67x458, 34x229\n" +
            "Band 3 Block=512x512 Type=Byte, ColorInterp=Blue\n" +
            "  NoData Value=0\n" +
            "  Overviews: 531x3657, 266x1829, 133x915, 67x458, 34x229\n";
    }
}