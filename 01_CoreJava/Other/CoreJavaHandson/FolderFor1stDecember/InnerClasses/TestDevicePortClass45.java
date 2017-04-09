
public class TestDevicePortClass45 {}

abstract class Device
	{	// Represents a common behaviour of all devices
		abstract class Port
			{	/*Represents a common behaviour of all ports*/	}
	}

class Printer extends Device
	{	// Specific behaviour for Printer among all devices
		class SerialPort extends Port
			{	/* Represents a common behaviour for all serial port*/ }
	}

class HighSpeedPrinter extends Printer
	{	class SerialPort extends Printer.SerialPort
			{}
	}