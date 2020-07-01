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
				bat "mvn clean install -DskipTests=true"
				echo "Build is Successful"
			}
		}
  	}
}
