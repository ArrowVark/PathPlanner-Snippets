public class RobotContainer
{
...


public Command getAutonomousCommand()
{
  // An example command will be run in autonomous
  return drivebase.getAutonomousCommand("New Auto");
}
}