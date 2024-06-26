public class DriveSubsystem extends SubsystemBase {
    public DriveSubsystem() {
      // All other subsystem initialization
      // ...
  
      // Configure AutoBuilder last
      AutoBuilder.configureHolonomic(
              this::getPose, // Robot pose supplier
              this::resetPose, // Method to reset odometry (will be called if your auto has a starting pose)
              this::getRobotRelativeSpeeds, // ChassisSpeeds supplier. MUST BE ROBOT RELATIVE
              this::driveRobotRelative, // Method that will drive the robot given ROBOT RELATIVE ChassisSpeeds
              new HolonomicPathFollowerConfig( // HolonomicPathFollowerConfig, this should likely live in your Constants class
                      new PIDConstants(5.0, 0.0, 0.0), // Translation PID constants
                      new PIDConstants(5.0, 0.0, 0.0), // Rotation PID constants
                      4.5, // Max module speed, in m/s
                      0.4, // Drive base radius in meters. Distance from robot center to furthest module.
                      new ReplanningConfig() // Default path replanning config. See the API for the options here
              ),
              () -> {
                // Boolean supplier that controls when the path will be mirrored for the red alliance
                // This will flip the path being followed to the red side of the field.
                // THE ORIGIN WILL REMAIN ON THE BLUE SIDE
  
                var alliance = DriverStation.getAlliance();
                if (alliance.isPresent()) {
                  return alliance.get() == DriverStation.Alliance.Red;
                }
                return false;
              },
              this // Reference to this subsystem to set requirements
      );
    }
  }