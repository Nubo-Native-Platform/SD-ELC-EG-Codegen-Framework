"""Print groupId:artifactId:version for a Maven pom.xml.

The groupId falls back to the <parent> groupId when the module doesn't declare its own.
Used by .github/workflows/ci-cd.yml to decide what's already published.
"""
import sys
import xml.etree.ElementTree as ET

NS = {"m": "http://maven.apache.org/POM/4.0.0"}


def text(root, path):
    value = root.findtext(path, namespaces=NS)
    return value.strip() if value else ""


root = ET.parse(sys.argv[1]).getroot()
group_id = text(root, "m:groupId") or text(root, "m:parent/m:groupId")
artifact_id = text(root, "m:artifactId")
version = text(root, "m:version") or text(root, "m:parent/m:version")
print(f"{group_id}:{artifact_id}:{version}")
