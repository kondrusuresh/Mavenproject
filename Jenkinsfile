pipeline 
{
  	agent any
  	stages 
  	{
   		stage('Build')
		{
			steps
			{
				echo "Build is Started"
				bat "mvn clean package -PRegression -DskipTests=true"
				echo "Build is Successful"
			}
		}
  	}
}
