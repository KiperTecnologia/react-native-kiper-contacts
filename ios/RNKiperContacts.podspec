
Pod::Spec.new do |s|
  s.name         = "RNKiperContacts"
  s.version      = "1.0.0"
  s.summary      = "RNKiperContacts"
  s.description  = <<-DESC
                  RNKiperContacts
                   DESC
  s.homepage     = ""
  s.license      = "MIT"
  # s.license      = { :type => "MIT", :file => "FILE_LICENSE" }
  s.author             = { "author" => "author@domain.cn" }
  s.platform     = :ios, "7.0"
  s.source       = { :git => "https://github.com/author/RNKiperContacts.git", :tag => "master" }
  s.source_files  = "RNKiperContacts/**/*.{h,m}"
  s.requires_arc = true


  s.dependency "React"
  #s.dependency "others"

end

  