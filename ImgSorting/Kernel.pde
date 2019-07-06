int matrixsize = 3;
// The convolution matrix kernels (filters).
float[] kernel;
      // Random
float[][] kernelA1 = { { 0, -1, 0 } , 
                     { -1, 9, -1 } ,
                     { 0, -1, -1 } } ;

      // Structures       
float[][] kernelA2 = { { -1, -1, 0 } , 
                     { -1, 9, -1 } ,
                     { 0, -1, 0 } } ;

      // Cubism        
float[][] kernelB = { { -1, -1, -1 } , 
                     { -1, 9, -1 } ,
                     { -1, -1, -1 } } ;

      // Dimention
float[][] kernelC = { { 0, -1, 0 } , 
                     { -1, 5, -1 } ,
                     { 0, -1, 0 } } ;  

      // Psy
float[][] kernelD = { { 0, 1, 0 } , 
                     { 1, 1, 1 } ,
                     { 0, 1, 0 } } ; 

      // FingerPrint
float[][] kernelE = { { -1, 1, 0 } , 
                     { 1, 1, 1 } ,
                     { 0, 1, 0 } } ; 
                     
      // Psy II
float[][] kernelF = { { 0, 1, 0 } , 
                     { 1, -1, 1 } ,
                     { 0, 1, 0 } } ; 
                     
      // Waves
float[][] kernelG = { { 0, 0, 0 } , 
                     { 1, -1, 1 } ,
                     { 0, 0, 0 } } ;   

      // Random Flowers
float[][] kernelH = { {0, 1, 0 } , 
                     { 1, 9, 1 } ,
                     { 0, 1, 0 } } ;  
                     
      // Random Flowers
float[][] kernelI = { {-2, -1, 0 } , 
                     { -1, 1, 1 } ,
                     { 0, 1, 2 } } ;  
                     
      // Diagonal Lines 
float[][] kernelJ = { {-2, 0, 3 } , 
                     { 0, 0, 0 } ,
                     { -3, 0, 2 } } ;

      // Lines I
float[][] kernelK = { {1, 1, 1 } , 
                      {1, 0, 1 } ,
                      {1, 1, 1 } } ;  


      // Lines II
float[][] kernelL = { {-2, -1, 3 } , 
                     { 1, 0, -1 } ,
                     { -3, 1, 2 } } ;  
                     
                                
float[][] matrix = kernelL;

                  
