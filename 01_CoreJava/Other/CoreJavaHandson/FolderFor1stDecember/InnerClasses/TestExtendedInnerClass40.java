

class OuterExtendible
	{	class InnerExtendible
			{}
	}

class A
	{	// Extending 1
		class B
			{}
	
		class C extends B
			{}
		
		// Extending 2
		class D extends OuterExtendible
			{}
		
		// class E extends InnerExtendible	{}  Not allowed.
	}

// Extending 3
class B extends OuterExtendible
	{	class C extends InnerExtendible
			{}	
	}

public class TestExtendedInnerClass40
{	}
