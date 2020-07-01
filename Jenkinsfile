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
				bat "mvn clean package -P Regression"
				echo "Build is Successful"
			}
		}
  	}
}
