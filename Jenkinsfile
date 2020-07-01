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
				bat "mvn clean package -PRegression"
				echo "Build is Successful"
			}
		}
  }
}
