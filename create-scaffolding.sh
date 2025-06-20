#!/bin/bash

set -euo pipefail

PROJECT_GROUP_ID="com.starlight"
PROJECT_ARTIFACT_ID="captainslog"
PROJECT_VERSION="1.0.0-SNAPSHOT"

POM_FILE="pom.xml"

# Check if pom.xml already exists
if [ -f "$POM_FILE" ]; then
  echo "⚠️  '$POM_FILE' already exists in $(pwd)"
  echo "   Skipping generation. To regenerate, delete the file manually."
  exit 0
fi

# Create temporary directory for archetype generation
TEMP_DIR=$(mktemp -d)
echo "📦 Using temp directory: $TEMP_DIR"

echo "🚀 Generating parent POM using Maven archetype..."
mvn archetype:generate \
  -DarchetypeGroupId=org.codehaus.mojo.archetypes \
  -DarchetypeArtifactId=pom-root \
  -DgroupId="$PROJECT_GROUP_ID" \
  -DartifactId="$PROJECT_ARTIFACT_ID" \
  -Dversion="$PROJECT_VERSION" \
  -DinteractiveMode=false \
  -DoutputDirectory="$TEMP_DIR"

# Move pom.xml to current directory
GENERATED_DIR="$TEMP_DIR/$PROJECT_ARTIFACT_ID"
if [ -f "$GENERATED_DIR/pom.xml" ]; then
  mv "$GENERATED_DIR/pom.xml" "$PWD"
  echo "✅ Moved parent pom.xml into $(pwd)"
else
  echo "❌ pom.xml not found in $GENERATED_DIR — generation may have failed."
  exit 1
fi

# Clean up temp directory
rm -rf "$TEMP_DIR"
echo "🧹 Cleaned up temp folder."

echo "👉 Next steps:"
echo "   mkdir domain application infrastructure boot"
echo "   Add each as a <module> in pom.xml"
