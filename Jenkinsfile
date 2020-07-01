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
				bat "mvn clean package"
				echo "Build is Successful"
			}
		}
  }
}
